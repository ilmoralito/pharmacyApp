package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.webflow.execution.RequestContext
import org.springframework.webflow.execution.RequestContextHolder

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
  def presentationService

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    getPresentationsByProduct:"GET",
    getMeasuresByPresentation:"GET"
	]

  def list() {
  	[orders:PurchaseOrder.list()]
  }

  def createFlow = {
  	init {
  		action {
  			List<Item> items = []

  			[items:items]
  		}

  		on("success").to "createPurchaseOrder"
  	}

  	createPurchaseOrder {
  		on("confirm") { PurchaseOrderCommand cmd ->
        println params.dump()
        println "+" * 100
        println cmd.dump()

        /*
        if (cmd.hasErrors()) {
          flow.errors = cmd
          flow.purchaseOrder = cmd

          return error()
        }

  			def purchaseOrder = new PurchaseOrder(
          deadline:cmd.dutyDate,
          invoiceNumber:cmd.invoiceNumber,
          typeOfPurchase:cmd.typeOfPurchase
        )

        flow.errors = null

  			[purchaseOrder:purchaseOrder]
        */
  		}.to "administeredItems"

  		on("cancel").to "done"
  	}

  	administeredItems {
  		on("addItem") {
        def item = new Item(params)

        if (item.hasErrors()) {
          item.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }
        flow.purchaseOrder.addToItems item

        flow.items << item
 			}.to "administeredItems"

      on("editPurchaseOrder").to "editPurchaseOrder"

 			on("deleteItem") {
        flow.items -= flow.items[params.int("index")]
			}.to "administeredItems"

			on("cancel").to "done"
  	}

    editPurchaseOrder {
      on("confirm") { PurchaseOrderCommand cmd ->
        if (cmd.hasErrors()) {
          flow.errors = cmd

          return error()
        }

        /*
        flow.purchaseOrder.dutyDate = params.date(dutyDate)
        flow.purchaseOrder.invoiceNumber = cmd.invoiceNumber
        flow.purchaseOrder.typeOfPurchase = cmd.typeOfPurchase
        */


      }.to "administeredItems"

      on("cancel").to "administeredItems" 
    }

  	done() {
  		redirect action:"list"
  	}
  }

  def getPresentationsByProduct(Integer productId) {
    def results = presentationService.presentationsByProduct productId

    if (!results) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      render(contentType:"application/json") {
        presentations = array {
          for(p in results) {
            presentation id:p.id, name:p.name, measures:p.measures
          }
        }
      }
    }
  }

  def getMeasuresByPresentation(Integer presentationId) {
    def results = presentationService.getMeasuresByPresentation(presentationId)

    if (!results) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      render(contentType:"application/json") {
        results
      }
    }
  }
}

class PurchaseOrderCommand implements Serializable {
  Date dutyDate
  String invoiceNumber
  String typeOfPurchase

  static constraints = {
    importFrom PurchaseOrder
  }
}
