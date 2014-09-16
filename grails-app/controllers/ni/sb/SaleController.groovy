package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET"
	]

  def list() {
  	def today = new Date()

  	[sales:Sale.salesFromTo(today, today).list()]
  }

  def createFlow = {
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
}
