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
  			def productsQuery = Item.where {
          purchaseOrder.status == true
        }

  			def clientsQuery = Client.where {
  				status == true
  			}

  			[products:productsQuery.list(), clients:clientsQuery.list()]
  		}

  		on("success").to "sale"
  	}

  	sale {
  		on("confirm") {

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

  def getProductData(Integer id) {
    def item = Item.get id

    if (!item) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      render(contentType:"application/json") {
        productId = item.product.id
        product = item.product.name
        presentation = item.presentation.name
        measure = item.measure
        quantity = item.quantity
        bash = item.bash
        sellingPrice = item.sellingPrice
      }
    }
  }
}
