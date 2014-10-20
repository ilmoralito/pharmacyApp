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

class SaleDetailCommand implements Serializable {
  Item item
  Presentation presentation
  String measure
  Integer quantity
  BigDecimal total = 1

  static constraints = {
    importFrom SaleDetail
  }
}
