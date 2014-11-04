package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
  def springSecurityService
  def saleService

	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"],
    getItemsByProduct:"GET",
    filterMedicinesByGenericName:"GET"
	]

  def list() {
  	def today = new Date()
    def users = User.list()
    def clients = Client.findAllByStatus(true)

    if (request.method == "POST") {
      println params
    }

  	[sales:Sale.salesFromTo(today, today + 1).list(), users:users, clients:clients]
  }

  def getItemsByProduct(Product product) {
    def query = Item.where {
      product == product
    }

    def items = query.list()

    items
  }

  def createSaleToClientFlow = {
    init {
      action {
        def clients = Client.where {
          status == true
        }

        def medicinesToSale = []
        def productsToSale = []
        def brandsToSale = []

        [clients:clients.list(), medicinesToSale:medicinesToSale, productsToSale:productsToSale, brandsToSale:brandsToSale]
      }

      on("success").to "selectCustomer"
    }

    selectCustomer {
      on("confirm") { SelectCustomer command ->
        if (command.hasErrors()) {
          command.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        [client:command.client, typeOfPurchase:command.typeOfPurchase]
      }.to "managePurchase"

      on("cancel").to "done"
    }

    managePurchase {
      action {
        def criteria = MedicineOrder.createCriteria()
        def medicines = criteria.list {
          projections {
            groupProperty "product"
          }
        }

        def genericNames = medicines.groupBy { it.genericName }.keySet() as List

        [medicines:medicines, genericNames:genericNames]
      }

      on("success").to "medicine"
    }

    medicine {
      on("filter") {
        def genericName = params?.genericName
        def criteria = MedicineOrder.createCriteria()
        def results = criteria.list {
          product {
            eq "name", params?.product
          }
        }

        def medicinesGrouped = results.groupBy { it.presentation }
        def medicinesFiltredByGenericName = genericName ? flow.medicines.findAll { it.genericName == genericName } : null

        [results:medicinesGrouped, product:params?.product, genericName:genericName, medicinesFiltredByGenericName:medicinesFiltredByGenericName]
      }.to "medicine"

      on("addItem") {
        def item = Item.get params?.id

        if (!item) { response.sendError 404 }

        flow.medicinesToSale = this.addItem(flow.medicinesToSale, item, params.int("quantity"))
      }.to "medicine"

      on("deleteItem") {
        def index = params.int("index")

        flow[params.targetList].remove index
      }.to "medicine"

      on("sell") {
        def user = springSecurityService.currentUser
        def balance = saleService.calcSaleBalance(flow.medicinesToSale, flow.productsToSale, flow.brandsToSale)
        def client = flow.client
        def typeOfPurchase = flow.typeOfPurchase

        def saleDetails = flow.medicinesToSale + flow.productsToSale + flow.brandsToSale

        if (!this.sale(user, balance, client, typeOfPurchase, saleDetails)) {
          return error()
        }
      }.to "done"

      on("selectCustomer").to "selectCustomer"
      on("medicine").to "managePurchase"
      on("manageProducts").to "manageProducts"
      on("manageBrands").to "manageBrands"
    }

    manageProducts {
      action {
        def items = Item.findAllByQuantityGreaterThan(0)

        [items:items]
      }

      on("success").to "product"
    }

    product {
      on("addItem") {
        def item = Item.get params?.id

        if (!item) { response.sendError 404 }

        flow.productsToSale = this.addItem(flow.productsToSale, item, params.int("quantity"))
      }.to "product"

      on("deleteItem") {
        def index = params.int("index")

       flow[params.targetList].remove index
      }.to "product"

      on("sell") {
        def user = springSecurityService.currentUser
        def balance = saleService.calcSaleBalance(flow.medicinesToSale, flow.productsToSale, flow.brandsToSale)
        def client = flow.client
        def typeOfPurchase = flow.typeOfPurchase

        def saleDetails = flow.medicinesToSale + flow.productsToSale + flow.brandsToSale

        if (!this.sale(user, balance, client, typeOfPurchase, saleDetails)) {
          return error()
        }
      }.to "done"

      on("selectCustomer").to "selectCustomer"
      on("medicine").to "managePurchase"
      on("manageProducts").to "manageProducts"
      on("manageBrands").to "manageBrands"
    }

    manageBrands {
      action {
        def brands = BrandProductOrder.findAllByQuantityGreaterThan(0).groupBy { it.brand }

        [brands:brands]
      }

      on("success").to "brand"
    }

    brand {
      on("addItem") {
        def item = Item.get params?.id

        if (!item) { response.sendError 404 }

        flow.brandsToSale = this.addItem(flow.brandsToSale, item, params.int("quantity"))
      }.to "brand"

      on("deleteItem") {
        def index = params.int("index")

        flow[params.targetList].remove index
      }.to "product"

      on("sell") {
        def user = springSecurityService.currentUser
        def balance = saleService.calcSaleBalance(flow.medicinesToSale, flow.productsToSale, flow.brandsToSale)
        def client = flow.client
        def typeOfPurchase = flow.typeOfPurchase

        def saleDetails = flow.medicinesToSale + flow.productsToSale + flow.brandsToSale

        if (!this.sale(user, balance, client, typeOfPurchase, saleDetails)) {
          return error()
        }
      }.to "done"

      on("selectCustomer").to "selectCustomer"
      on("medicine").to "managePurchase"
      on("manageProducts").to "manageProducts"
      on("manageBrands").to "manageBrands"
    }

    done() {
      redirect controller:"sale", action:"list"
    }
  }
  
  def sale(User user, def balance, Client client, String typeOfPurchase, def saleDetails) {
    def saleToClient = new SaleToClient(user:user, balance:balance, client:client, typeOfPurchase:typeOfPurchase, status:typeOfPurchase == "Contado" ? "Cancelado" : "Pendiente")

    saleDetails.each { saleDetail ->
      //update item quantity
      saleDetail.item.quantity -= saleDetail.quantity

      //add saleDetail to Sale
      saleToClient.addToSaleDetails saleDetail
    }

    if (!saleToClient.save()) {
      saleToClient.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }

      return false
    }

    true
  }

  def addItem(def items, Item item, Integer quantity) {
    def itemToSale = items.find { it.item == item }

    if (itemToSale) {
      items -= itemToSale
    }

    def totalToPay = item.sellingPrice * quantity

    def saleDetail = new SaleDetail(item:item, quantity:quantity, total:totalToPay)

    items << saleDetail

    items
  }

  def filterMedicinesByGenericName(String genericName) {
    def criteria = MedicineOrder.createCriteria()
    def results = criteria.list {
      product {
        eq "genericName", genericName
      }

      projections {
        groupProperty "product"
      }
    }

    render(contentType:"application/json") {
      results
    }
  }
}

class SelectCustomer implements Serializable {
  Client client
  String typeOfPurchase

  static constraints = {
    importFrom SaleToClient
  }
}

class SaleDetailCommand implements Serializable {
  Item item
  Integer quantity
  BigDecimal total = 1

  static constraints = {
    importFrom SaleDetail
  }
}
