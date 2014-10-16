package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
  def presentationService

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    getPresentationsByProduct:"GET",
    getMeasuresByPresentation:"GET",
    getBrandsByBrandProduct:"GET",
    getDetailsInBrand:"GET"
	]

  def list() {
    def status = (!params?.option?true:"")

    switch(params?.option) {
      case "true":
        status = true
      break
      case "false":
        status = false
      break
      case "Contado":
        status = params.option
      break
      case "Credito":
        status = params.option
      break
    }

    if (status == true || status == false) {
      [orders:PurchaseOrder.findAllByStatus(status), option:status]
    } else {
      [orders:PurchaseOrder.findAllByTypeOfPurchase(status), option:status]
    }
  }

  def createFlow = {
    init {
      action {
        flow.medicines = []
        flow.products = []
        flow.brandProductsOrders = []
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
            it.bash.clearTime() == params.date("bash", "yyyy-MM-dd").clearTime()
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
        this.deleteItem(params.int("index"), flow.medicines, flow.purchaseOrder)
      }.to "medicine"

      on("complete") {
        this.savePurchaseOrder(flow.medicines, flow.brandProductsOrders, flow.products, flow.purchaseOrder)
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
        if (!params?.quantity || !params?.purchasePrice || !params?.sellingPrice) { return error() }

        if (flow.products) {
          def productInstance = flow.products.find { 
            it.product == Product.get(params.int("product"))
          }

          if (productInstance) {
            flow.products -= productInstance
            flow.purchaseOrder.balance -= productInstance.total
          }
        }

        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        def product = new Item(params)

        if (!product.validate(["product", "quantity", "purchasePrice", "sellingPrice"])) {
          product.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + product.total

        flow.products << product
      }.to "product"

      on("deleteItem") {
        this.deleteItem(params.int("index"), flow.products, flow.purchaseOrder)
      }.to "product"

      on("complete") {
        this.savePurchaseOrder(flow.medicines, flow.brandProductsOrders, flow.products, flow.purchaseOrder)
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
        //process
        //0. check for quantity, purchasePrice and sellingPrice are in params
        //1. Check if current brandProductOrder is already created if it is the case then delete it and update balance
        //2. Calculate brandProductOrder total property
        //3. Create new brandProductOrder
        //4. Update purchaseOrder balance
        //5. Add brandProductOrder to brandProductsOrders list

        //0
        if (!params?.quantity || !params?.purchasePrice || !params?.sellingPrice) { return error() }

        //1
        if (flow.brandProductsOrders) {
          def brandProductOrderInstance = flow.brandProductsOrders.find { 
            it.product == Product.get(params.int("product")) &&
            it.brand == Brand.get(params.int("brand")) &&
            it.detail == params?.detail
          }

          if (brandProductOrderInstance) {
            flow.brandProductsOrders -= brandProductOrderInstance
            flow.purchaseOrder.balance -= brandProductOrderInstance.total
          }
        }

        //2
        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        //3
        def brandProductOrder = new BrandProductOrder(params)

        if (!brandProductOrder.validate(["product", "brand", "detail", "quantity", "purchasePrice", "sellingPrice"])) {
          brandProductOrder.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        //4
        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + brandProductOrder.total

        //5
        flow.brandProductsOrders << brandProductOrder
      }.to "brand"

      on("deleteBrandProductOrder") {
        this.deleteItem(params.int("index"), flow.brandProductsOrders, flow.purchaseOrder)
      }.to "brand"

      on("complete") {
        this.savePurchaseOrder(flow.medicines, flow.brandProductsOrders, flow.products, flow.purchaseOrder)
      }.to "done"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    editPurchaseOrder {
      on("confirm") {
        //get current purchase order provider
        def persistentValue = flow.purchaseOrder.provider

        flow.purchaseOrder.properties = params

        if (!flow.purchaseOrder.validate()) {
          flow.errors = flow.purchaseOrder
          return error()
        }

        //check if provider was changed if it is true then set to null items container
        def provider = Provider.get params.int("provider")
        if (persistentValue != provider) {
          flow.medicines = []
          flow.products = []
          flow.brandProductsOrders = []
        }

        flow?.errors?.clearErrors()
      }.to "medicine"

      on("cancel").to "medicine"
    }

  	done() {
  		redirect action:"list"
  	}
  }

  def editFlow = {
    init {
      action {
        flow.purchaseOrder = PurchaseOrder.get params.int("id")
        flow.providers = Provider.findAllStatus true

        if (!flow.purchaseOrder) {
          response.sendError 404
        }

        flow.medicines = flow.purchaseOrder?.items?.findAll { it instanceof ni.sb.MedicineOrder }
        flow.products = flow.purchaseOrder?.items?.findAll { !(it instanceof ni.sb.MedicineOrder) || !(it instanceof ni.sb.BrandProductOrder) }
        flow.brandProductsOrders = flow.purchaseOrder?.items?.findAll { it instanceof ni.sb.BrandProductOrder }
      }

      on("success").to "medicine"
    }

    medicine {

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    product {

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    brand {

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    editPurchaseOrder {
      on("confirm") {
        //get current purchase order provider
        def persistentValue = flow.purchaseOrder.provider

        flow.purchaseOrder.properties = params

        if (!flow.purchaseOrder.save()) {
          flow.errors = flow.purchaseOrder
          return error()
        }

        //check if provider was changed if it is true then set to null items container
        def provider = Provider.get params.int("provider")
        if (persistentValue != provider) {
          //delete all items from current purchaseOrder instance
          //flow.purchaseOrder.items.clear()

          //reset all list variables
          flow.medicines = []
          flow.products = []
          flow.brandProductsOrders = []
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

  //@id params is BrandProduct instanance id
  def getBrandsByBrandProduct(Integer id) {
    def brandProduct = BrandProduct.get id
    def brands = brandProduct.brands

    if (!brands) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      render(contentType:"application/json") {
        brands
      }
    }
  }

  //@id params is Brand instanance id
  def getDetailsInBrand(Integer id) {
    def brand = Brand.get id
    def details = brand.details

    if (!details) {
      render(contentType:"application/json") {
        status = false
      }
    } else {
      render(contentType:"application/json") {
        details
      }
    }
  }

  private savePurchaseOrder(List medicines, List brandProductsOrders, List products, PurchaseOrder purchaseOrder) {
    if (medicines) {
      medicines.each { medicine ->
        purchaseOrder.addToItems medicine
      }
    }

    if (products) {
      products.each { product ->
        purchaseOrder.addToItems product
      }
    }

    if (brandProductsOrders) {
      brandProductsOrders.each { brandProductOrder ->
        purchaseOrder.addToItems brandProductOrder
      }
    }

    if (purchaseOrder.save(flush:true)) {
      purchaseOrder.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }
    }
  }

  private deleteItem(Integer index, List items, PurchaseOrder purchaseOrder) {
    purchaseOrder.balance -= items[index].total
    items.remove index
  }
}
