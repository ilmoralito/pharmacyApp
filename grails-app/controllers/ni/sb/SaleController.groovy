package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
	]

  def list() {
  	def today = new Date()

  	[sales:Sale.salesFromTo(today, today).list()]
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
  		on("selectProduct") {
        def product = params?.product
        def query = Item.where {
          product.name == product
        }

        def items = query.list()

        [productsInStock:items.groupBy { it.presentation }, product:product]
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
        def target = flow.sales.find {
          it.item == item && it.presentation == presentation && it.measure == measure && it.item.bash == item.bash
        }

        //if above condition is true then removed
        if (target) {
          flow.sales -= target
        }

        flow.sales << saleDetail
      }.to "sale"

  		on("delete") {
        flow.sales.remove params.int("index")
  		}.to "sale"

  		//moves
  		on("saleToClient").to "saleToClient"
  		on("cancel").to "done"
  	}

  	saleToClient {
  		on("confirm") {

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
}

class SaleDetailCommand implements Serializable {
  Product product
  Presentation presentation
  String measure
  Integer quantity
  BigDecimal total = 1

  static constraints = {
    importFrom SaleDetail
  }

  def calcTotal() {

  }
}
