package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.webflow.execution.RequestContext
import org.springframework.webflow.execution.RequestContextHolder

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET"
	]

  def list() {
  	[orders:PurchaseOrder.list()]
  }

  def createFlow = {
  	init {
  		action {
  			List<Item> items = []
  			
  			//TODO
  			RequestContext requestContext = RequestContextHolder.getRequestContext()

  			[items:items]
  		}

  		on("success").to "createPurchaseOrder"
  	}

  	createPurchaseOrder {
  		on("confirm") {
  			params.deadline = params.date("deadline", "yyyy-MM-dd")
  			def purchaseOrder = new PurchaseOrder(params)

  			if (!purchaseOrder.validate()) {
  				purchaseOrder.errors.allErrors.each { error ->
  					log.error "[$error.field: $error.defaultMessage]"
  				}

  				return error()
  			}

  			[purchaseOrder:purchaseOrder]
  		}.to "administeredItems"

  		on("cancel").to "done"
  	}

  	administeredItems {
  		on("addItem") {

 			}.to "administeredItems"

 			on("deleteItem") {

			}.to "administeredItems"

			on("cancel").to "done"
  	}

  	done() {
  		redirect action:"list"
  	}
  }
}
