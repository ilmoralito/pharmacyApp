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
    def status = (params?.option == "true") ? true : (params?.option == "false") ? false : params?.option

    if (status == true || status == false) {
      [orders:PurchaseOrder.findAllByStatus(status)]
    } else {
      [orders:PurchaseOrder.findAllByTypeOfPurchase(status)]
    }
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
    init {
      action {
        flow.medicines = []
        flow.products = []
        flow.brands = []
        flow.providers = Provider.findAllStatus true
      }

      on("success"). to "createPurchaseOrder"
    }

  	createPurchaseOrder {
  		on("confirm") {
  			def purchaseOrder = new PurchaseOrder(
          provider:params?.provider,
          dutyDate:params?.dutyDate,
          invoiceNumber:params?.invoiceNumber,
          typeOfPurchase:params?.typeOfPurchase,
          status:(params?.typeOfPurchase == "Contado") ? true : false //TODO:Implement this from purchaseOrder beforeInstance event
        )

        if (!purchaseOrder.validate()) {
          flow.errors = purchaseOrder
          return error()
        }

        flow?.errors?.clearErrors()

  			[purchaseOrder:purchaseOrder]
  		}.to "medicine"

  		on("cancel").to "done"
  	}

    //MEDICINE
  	medicine {
  		on("addItem") {
        //check if new medicine already exist. If it is true then delete it from flow.medicines and then recreate it
        if (flow.medicines) {
          def medicineInstance = flow.medicines.find { 
            it.product == Product.get(params.int("product")) &&
            it.presentation == Presentation.get(params.int("presentation")) &&
            it.measure == params?.measure &&
            it.bash == params.date("bash", "yyyy-MM-dd")
          }
        
          if (medicineInstance) {
            flow.medicines -= medicineInstance
            flow.purchaseOrder.balance -= medicineInstance.total
          }
        }
        
        //calculate total
        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        def medicine = new MedicineOrder(params)

        if (!medicine.validate(["product", "presentation", "measure", "quantity", "purchasePrice", "sellingPrice", "bash"])) {
          medicine.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        //update purchace order balance property
        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + medicine.total

        flow.medicines << medicine
 			}.to "medicine"

      on("deleteMedicine") {
        def index = params.int("index")

        flow.purchaseOrder.balance -= flow.medicines[index].total
        flow.medicines.remove index
      }.to "medicine"

      on("complete") {
        
      }.to "done"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
			on("cancel").to "done"
  	}

    //PRODUCT
    product {
      on("addItem") {

      }.to "product"

      on("deleteItem") {

      }.to "product"

      on("complete") {
        
      }.to "done"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    //BRAND
    brand {
      on("addItem") {

      }.to "brand"

      on("deleteItem") {

      }.to "brand"

      on("complete") {
        
      }.to "done"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
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
      }.to "medicine"

      on("cancel").to "medicine"
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
}
