package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    getProductData:"GET"
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

  			[products:products, clients:clientsQuery.list()]
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

  		on("delete") {

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

  def getProductData(String product) {
    /*
    def query = Item.where {
      product.name == product
    }

    def results = query.list()

    if (!results) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      def g = results.groupBy { it.presentation }

      render(contentType:"application/json") {
        g
      }
    }

    */
  }
}
