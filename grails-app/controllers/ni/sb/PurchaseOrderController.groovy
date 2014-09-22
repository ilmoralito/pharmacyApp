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
    edit:"GET",
    editItem:"GET",
    getPresentationsByProduct:"GET",
    getMeasuresByPresentation:"GET"
	]

  def list() {
  	[orders:PurchaseOrder.list()]
  }

  def show(Integer id){
    def purchaseOrder = PurchaseOrder.get id
    if (!purchaseOrder) { response.sendError 404}
    [purchaseOrder:purchaseOrder]
  }

  def update(Integer id){
    def purchaseOrder = PurchaseOrder.get id
    if (!purchaseOrder) { response.sendError 404}
    purchaseOrder.properties = params

    if (!purchaseOrder.save()) {
      render(view:"show", model:[id:id, purchaseOrder:purchaseOrder])
      return
    }

    flash.message = "Actualizado"
    redirect action:"show", id:id
  }

  def edit(Integer id){
    def purchaseOrder = PurchaseOrder.get id
    if (!purchaseOrder) {response.sendError 404}
    def itemInstance = Item.findAllByPurchaseOrder(purchaseOrder)
    [itemInstance:itemInstance, purchaseOrder:purchaseOrder]
  }

  def editItem(Integer id){
    def itemInstance = Item.get id
    if (!itemInstance) {response.sendError 404}
    def product = Product.get(itemInstance.product.id)
    def presentations = Presentation.findAllByProduct(product)
    [itemInstance:itemInstance, presentations:presentations]
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

        //check if new item already exist. If it is true then delete item from items and then recreate it
        def itemInstance = this.getItemFromItems(params.int("product"), params.int("presentation"), params?.measure, params.date("bash", "yyyy-MM-dd"), flow.purchaseOrder.items)

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
        def product = params.int("product")
        def presentation = params.int("presentation")
        def measure = params?.measure
        def bash = params.date("bash", "yyyy-MM-dd")
        def items = flow.purchaseOrder.items

        def itemInstance = this.getItemFromItems(product, presentation, measure, bash, items)

        //if there exist item then remove it from items in purchase order
        if (itemInstance) {
          flow.purchaseOrder.items -= itemInstance
        } else {
          response.sendError 404
          return error()
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

  def getPresentationsByProduct(Integer id) {
    def results = presentationService.presentationsByProduct id

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

  private getItemFromItems(Integer productId, Integer presentationId, String measure, Date bash, items) {
    def product = Product.get productId
    def presentation = Presentation.get presentationId

    def item = items.find { itemInstance ->
      itemInstance.product == product &&
      itemInstance.presentation == presentation &&
      itemInstance.measure == measure &&
      itemInstance.bash.clearTime() == bash.clearTime()
    }

    item
  }
}
