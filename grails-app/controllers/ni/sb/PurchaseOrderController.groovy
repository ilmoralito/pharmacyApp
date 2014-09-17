package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
  def presentationService

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    show:"GET",
    update:"POST",
    getPresentationsByProduct:"GET",
    getMeasuresByPresentation:"GET"
	]

  def list() {
  	[orders:PurchaseOrder.list()]
  }

  def show(Integer id){
    def purchaseOrder = PurchaseOrder.get(params.id)
    if (!purchaseOrder) { response.sendError 404}
    [purchaseOrder:purchaseOrder]
  }

  def update(Integer id){
    def purchaseOrder = PurchaseOrder.get(params.id)
    if (!purchaseOrder) { response.sendError 404}
    purchaseOrder.properties = params

    if (!purchaseOrder.save()) {
      render(view:"show", model:[id:id, purchaseOrder:purchaseOrder])
      return
    }

    flash.message = "Actualizado"
    redirect action:"show", id:id
  }

  def createFlow = {
  	createPurchaseOrder {
  		on("confirm") {
  			def purchaseOrder = new PurchaseOrder(
          dutyDate:params?.dutyDate,
          invoiceNumber:params?.invoiceNumber,
          typeOfPurchase:params?.typeOfPurchase
        )

        if (!purchaseOrder.validate()) {
          flow.errors = purchaseOrder
          return error()
        }

        flow?.errors?.clearErrors()

  			[purchaseOrder:purchaseOrder]
  		}.to "administeredItems"

  		on("cancel").to "done"
  	}

  	administeredItems {
  		on("addItem") {
        //calculate total
        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        //check if new item already exist if it is true then delete item in items and then recreate item besides update purchase order balance
        def itemInstance = this.getItemFromItems(params.int("product"), params.int("presentation"), params?.measure, params?.bash, flow.purchaseOrder.items)

        if (itemInstance) {
          flow.purchaseOrder.items -= itemInstance
          flow.purchaseOrder.balance -= itemInstance.total
        }

        def item = new Item(params)

        if (!item.validate(["product", "presentation", "measure", "quantity", "purchasePrice", "sellingPrice", "bash"])) {
          item.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        //update purchace order balance property
        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + item.total

        //add item to current purchase order instance
        flow.purchaseOrder.addToItems item
 			}.to "administeredItems"

      on("editPurchaseOrder").to "editPurchaseOrder"

 			on("deleteItem") {
        //get item from purchase order items
        def itemInstance = this.getItemFromItems(params.int("product"), params.int("presentation"), params?.measure, params?.bash, flow.purchaseOrder.items)

        //if there exist item then remove it from items in purchase order
        if (itemInstance) {
          flow.purchaseOrder.items -= itemInstance
        } else {
          response.sendError 404
        }

        //update purchase order balance
        flow.purchaseOrder.balance -= itemInstance.total
      }.to "administeredItems"

      on("complete") {
        if (!flow.purchaseOrder.save(flush:true)) {
          flow.purchaseOrder.errors.allErrors.each { error ->
            log.error "[$error.field:$error.defaultMessage]"
          }

          return error()
        }
      }.to "done"

			on("cancel").to "done"
  	}

    editPurchaseOrder {
      on("confirm") {
        flow.purchaseOrder.properties = params

        if (!flow.purchaseOrder.validate()) {
          flow.errors = flow.purchaseOrder
          return error()
        }

        flow?.errors?.clearErrors()
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

  private getItemFromItems(Integer productId, Integer presentationId, String measure, String bash, items) {
    def product = Product.get productId
    def presentation = Presentation.get presentationId

    def item = items.find { itemInstance ->
      itemInstance.product == product && itemInstance.presentation == presentation && itemInstance.measure == measure && itemInstance.bash == bash
    }

    item
  }
}
