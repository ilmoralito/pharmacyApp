package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
  def springSecurityService
  def saleService

	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"],
    show:"GET",
    cancelSale:"GET",
    pay:["GET", "POST"],
    getItemsByProduct:"GET",
    filterMedicinesByGenericName:"GET",
    addClient:"GET"
	]

  def list() {
  	def today = new Date()
    def users = User.list()
    def clients = Client.findAllByStatus(true, [sort:"fullName", order:"asc"])
    def sales = []

    if (request.method == "POST") {
      def criteria = Sale.createCriteria()
      sales = criteria {
        //filter between dates
        if (params?.from && params?.to) {
          ge "dateCreated", params.date("from", "yyyy-MM-dd").clearTime()
          le "dateCreated", params.date("to", "yyyy-MM-dd").clearTime() + 1
        }

        //filter by client(s)
        if (params?.clients) {
          def clientsInstance = Client.getAll params.list("clients")

          "in" "client", clientsInstance
        }

        //filter by typeofpurchase
        if (params?.cash && params?.credit) {
          or {
            eq "typeOfPurchase", params.cash
            eq "typeOfPurchase", params.credit
          }
        }

        if (params?.cash && !params?.credit || params?.credit && !params?.cash) {
          def typeOfPurchase = params?.cash ?: params?.credit

          eq "typeOfPurchase", typeOfPurchase
        }

        //filter by status
        if (params?.isPending && params?.isCanceled) {
          or {
            eq "status", params.isPending
            eq "status", params.isCanceled
          }
        }

        if (params?.isPending && !params?.isCanceled || params?.isCanceled && !params?.isPending) {
          def status = params?.isPending ?: params?.isCanceled

          eq "status", status
        }

        //filter by canceled
        if (params?.canceled) {
          eq "canceled", true
        }

        //filter by users(sellers)
        if (params?.users) {
          def usersInstance = User.getAll params.list("users")

          "in" "user", usersInstance
        }

        order "id", "desc"
      }
    } else {
      sales = Sale.fromTo(today, today + 1).list(sort:"id", order:"desc")
    }

    def todaySaleAmount = Sale.fromTo(today, today + 1).findAllByCanceled(false).balance.sum() ?: 0
    def amountOfDailyExpenses = Daily.fromTo(today, today + 1).get().expenses.quantity.sum() ?: 0
    def inBox = todaySaleAmount - amountOfDailyExpenses

  	[
      sales: sales,
      users:users,
      clients:clients,
      todaySaleAmount:todaySaleAmount,
      amountOfDailyExpenses:amountOfDailyExpenses,
      inBox:inBox,
      amount:sales.findAll{ !it.canceled }.balance.sum()
    ]
  }

  def show(Integer id) {
    def sale = Sale.get id
    def details = sale.saleDetails

    def medicinesToSale = details.findAll { it.item.instanceOf(ni.sb.MedicineOrder) }
    def productsToSale = details.findAll { !(it.item.instanceOf(ni.sb.MedicineOrder)) && !(it.item.instanceOf(ni.sb.BrandProductOrder)) }
    def brandsToSale = details.findAll { it.item.instanceOf(ni.sb.BrandProductOrder) }

    [sale:sale, medicinesToSale:medicinesToSale, productsToSale:productsToSale, brandsToSale:brandsToSale]
  }

  @Secured(["ROLE_ADMIN"])
  def cancelSale(Integer id) {
    def sale = Sale.get id

    if (!sale || sale.canceled) {
      response.sendError 404
    }

    sale.canceled = true

    sale.save(flush:true)

    redirect action:"show", id:id
  }

  def addClient(String fullName, String address, String identificationCard, String phones) {
    def client = new Client(fullName:fullName, address:address, identificationCard:identificationCard, phones:params.list("phones"))

    if (!client.save(flush:true)) {
      client.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }
      return false
    }

    render(contentType:"application/json") {
      client
    }
  }

  def getItemsByProduct(Product product) {
    def query = Item.where {
      product == product
    }

    def items = query.list()

    items
  }

  def pay(Integer id){
    def saleInstance = SaleToClient.get id
    def payInstance = Pay.findAllBySaleToClient(saleInstance, [sort: "dateCreated", order: "asc"])
    def user = springSecurityService.currentUser

    if (request.method == 'GET') {
      [saleInstance:saleInstance, payInstance:payInstance]
    }else{
      def pay = new Pay(user:user, receiptNumber:params.receiptNumber, payment:params.payment, change:params.change, saleToClient:saleInstance)

      if (!pay.save()) {
        pay.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"}
      }else{
        flash.message = "El abono fue registrado correctamente!!"
        if (params.balance.toBigDecimal() == params.payment.toBigDecimal()) {
          saleInstance.properties["status"] = "Cancelado"
        }
        redirect(action:"pay", params:[id:params.id])
      }

    }
  }

  def delete(){
    def payInstance = Pay.get(params.idPay)
    def saleInstance = SaleToClient.get(params.id)

    if (request.method == 'GET') {
      [payInstance:payInstance, saleInstance:saleInstance]
    }else{
      payInstance.delete(flush:true)
      saleInstance.properties["status"] = "Pendiente"
      flash.message="El abono ha sido borrado correctamente!!"
      redirect(action:"pay", params:[id:params.id])
    }
  }

  def createSaleToClientFlow = {
    init {
      action {
        def clients = Client.findAllByStatus(true, [sort:"fullName", order:"asc"])

        def medicinesToSale = []
        def productsToSale = []
        def brandsToSale = []

        [clients:clients, medicinesToSale:medicinesToSale, productsToSale:productsToSale, brandsToSale:brandsToSale]
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

      on("confirmGeneralSale").to "managePurchase"

      on("cancel").to "done"
    }

    managePurchase {
      action {
        def criteria = MedicineOrder.createCriteria()
        def medicines = criteria.list {
          gt "quantity", 0

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
        def c = MedicineOrder.createCriteria()
        def results = c {
          product {
            eq "name", params?.product
          }
        }.groupBy({ it.presentation }, { it.measure })

        def medicinesFiltredByGenericName = genericName ? flow.medicines.findAll { it.genericName == genericName } : null

        [results:results, product:params?.product, genericName:genericName, medicinesFiltredByGenericName:medicinesFiltredByGenericName]
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
        def criteria = Item.createCriteria()
        def items = criteria {
          gt "quantity", 0

          projections {
            groupProperty "product", "product"
          }

          order "product"
        }

        [items:items.findAll { !(it.instanceOf(ni.sb.Medicine)) && !(it.instanceOf(ni.sb.BrandProduct))}]
      }

      on("success").to "product"
    }

    product {
      on("filter") {
        def p = params?.product
        def criteria = Item.createCriteria()
        def itemsByProductName = criteria {
          product {
            eq "name", p
          }
        }

        [itemsByProductName:itemsByProductName, product:p]
      }.to "product"

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
        def c = BrandProductOrder.createCriteria()
        def brandProductOrders = c {
          projections {
            groupProperty "product"
          }
        }

        //i do not know why but this fix the problem
        println brandProductOrders

        [brandProductOrders:brandProductOrders]
      }
      on("success").to "brand"
    }

    brand {
      on("filter") {
        def p = params?.product
        def criteria = BrandProductOrder.createCriteria()
        def brandProductOrderByBrand = criteria {
          product {
            eq "name", p
          }
        }.groupBy ({ it.brand }, { it.product.name })

        [brandProductOrderByBrand:brandProductOrderByBrand, product:p]
      }.to "brand"

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
    Sale sale

    if (client && typeOfPurchase) {
      sale = new SaleToClient(user:user, balance:balance, client:client, typeOfPurchase:typeOfPurchase, status:typeOfPurchase == "Contado" ? "Cancelado" : "Pendiente")
    } else {
      sale = new Sale(user:user, balance:balance, status:"Cancelado")
    }

    saleDetails.each { saleDetail ->
      //update item quantity
      saleDetail.item.quantity -= saleDetail.quantity

      //add saleDetail to Sale
      sale.addToSaleDetails saleDetail
    }

    if (!sale.save()) {
        sale.errors.allErrors.each { error ->
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