package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
  def springSecurityService

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
	]

  def list() {
  	def today = new Date()

  	[sales:Sale.salesFromTo(today, today + 1).list()]
  }

  def createFlow = {
  	init {
  		action {
        //get product name
        def criteria = Item.createCriteria()
        def products = criteria.list {
          purchaseOrder {
            eq "status", true
          }

          projections {
            groupProperty "product"
          }
        }

        //get all clients available to buy
  			def clientsQuery = Client.where {
  				status == true
  			}

        //list of sales added
        def sales = []

  			[products:products, clients:clientsQuery.list(), sales:sales]
  		}

  		on("success").to "sale"
  	}

  	sale {
  		on("selectProduct") { SelectProductCommand command ->
        if (command.hasErrors()) {
          command.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        def items = this.getItemsByProduct(command.product)
       
        [productsInStock:items.groupBy { it.presentation }, product:command.product]
  		}.to "sale"

      on("confirm") {
        def item = Item.get params.int("item")
        def presentation = Presentation.get params.int("presentation")
        def measure = params?.measure
        def quantity = params.int("quantity")
        def total = quantity * item.sellingPrice

        def saleDetail = new SaleDetail(item:item, presentation:presentation, measure:measure, quantity:quantity, total:total)

        if (!saleDetail.validate(["item", "presentation", "measure", "quantity", "total"])) {
          saleDetail.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        //check if current item is in sales
        def target = flow.sales.find { it.item == item }

        //if above condition is true then removed
        if (target) { flow.sales -= target }

        flow.sales << saleDetail
      }.to "sale"

  		on("delete") {
        flow.sales.remove params.int("index")
  		}.to "sale"

      on("confirmSale") {
        def sale = new Sale(user:springSecurityService.currentUser, balance:flow.sales.total.sum())

        flow.sales.each { saleInstance ->
          //get item instance in order to update stock
          saleInstance.item.quantity -= saleInstance.quantity

          sale.addToSaleDetails saleInstance
        }

        sale.save(flush:true)
      }.to "done"

  		//moves
  		on("saleToClient").to "saleToClient"
  		on("cancel").to "done"
  	}

  	saleToClient {
      on("chooseClient") { SaleToClientCommand command ->
        if (command.hasErrors()) {
          command.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        [client:command.client, typeOfPurchase:command.typeOfPurchase]
      }.to "saleToClient"

  		on("selectProduct") { SelectProductCommand command ->
        if (command.hasErrors()) {
          command.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        def items = this.getItemsByProduct(command.product)
       
        [productsInStock:items.groupBy { it.presentation }, product:command.product]
  		}.to "saleToClient"

  		on("delete") {

  		}.to "saleToClient"

  		//moves
  		on("sale").to "sale"
  		on("cancel").to "done"
  	}

  	done() {
  		redirect action:"list"
  	}
  }

  def getItemsByProduct(Product product) {
    def query = Item.where {
      product == product
    }

    def items = query.list()

    items
  }
}

class SaleToClientCommand implements Serializable {
  Client client
  String typeOfPurchase

  static constraints = {
    importFrom SaleToClient
  }
}

class SelectProductCommand implements Serializable {
  Product product

  static constraints = {
    product nullable:false, validator: { product ->
      if (!product.status) {
        "selectProductCommand.product.notValidProduct"
      }
    }
  }
}
