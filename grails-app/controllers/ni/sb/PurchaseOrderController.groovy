package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
  def presentationService

	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"],
    stock:"GET",
    getPresentationsByProduct:"GET",
    getMeasuresByPresentation:"GET",
    getBrandsByBrandProduct:"GET",
    getDetailsInBrand:"GET",
    addProduct:"GET"
	]

  def list() {
    def orders = []

    if (request.method == "POST") {
      def criteria = PurchaseOrder.createCriteria()
      orders = criteria {
        //filter by store name
        if (params?.stores) {
          "in" "store", params?.stores
        }

        /*
        //filter by providers
        if (params?.providers) {
          def providerInstances = Provider.getAll params.list("providers")

          "in" "provider", providerInstances
        }

        //filter by typeofpurchase
        if (params?.cash && params?.credit) {
          or {
            eq "typeOfPurchase", params.cash
            eq "typeOfPurchase", params.credit
          }
        }
        */

        if (params?.cash && !params?.credit || params?.credit && !params?.cash) {
          def typeOfPurchase = params?.cash ?: params?.credit

          eq "typeOfPurchase", typeOfPurchase
        }

        //filter by status
        if (params?.pending && params?.canceled) {
          or {
            eq "status", params.boolean("pending")
            eq "status", params.boolean("canceled")
          }
        }

        if (params?.pending && !params?.canceled || params?.canceled && !params?.pending) {
          def status = params.boolean("pending") ?: params.boolean("canceled")

          eq "status", status
        }
      }
    } else {
      orders = PurchaseOrder.list()
    }

    def providers = Provider.findAllByStatus(true)
    def stores = Distributor.list().name + providers.name

    [orders:orders, providers:providers, stores:stores]
  }

  def stock() {
    //items
    def criteria = Item.createCriteria()
    def result = criteria {
      gt "quantity", 0L

      projections {
        groupProperty "product", "product"
        sum "quantity", "quantity"
      }

      order "product"
      resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
    }

    def items = result.findAll { !(it.product.instanceOf(ni.sb.Medicine)) && !(it.product.instanceOf(ni.sb.BrandProduct)) }

    items.collect { item ->
      def target = Item.findByProduct (item.product)

      item["sellingPrice"] = target.sellingPrice
      item["location"] = target.product.location
      item["total"] = target.sellingPrice * item.quantity
    }

    //medicines
    def medicineOrderCriteria = MedicineOrder.createCriteria()
    def medicines = medicineOrderCriteria.list {
      gt "quantity", 0

      projections {
        groupProperty "product"
      }
    }

    //brands
    def brandProductOrderCriteria = BrandProductOrder.createCriteria()
    def brandProducts = brandProductOrderCriteria {
      gt "quantity", 0

      projections {
        groupProperty "product"
      }
    }

    [items:items, medicines:medicines, brandProducts:brandProducts]
  }

  def createFlow = {
    init {
      action {
        flow.medicines = []
        flow.products = []
        flow.brandProductsOrders = []
        flow.providers = Provider.findAllStatus true
        flow.dealers = flow.providers.name + Distributor.list().name
      }

      on("success"). to "createPurchaseOrder"
    }

  	createPurchaseOrder {
  		on("confirm") {
        //create purchase order
  			def purchaseOrder = new PurchaseOrder (
          store:params?.store,
          dutyDate:params?.dutyDate,
          invoiceNumber:params?.invoiceNumber,
          typeOfPurchase:params?.typeOfPurchase,
          status:(params?.typeOfPurchase == "Contado") ? true : false
        )

        //add providers to purchase order instance
        params.list("providers").each { provider ->
          def providerInstance = Provider.findByName provider

          if (provider) {
            purchaseOrder.addToProviders providerInstance
          }
        }

        if (!purchaseOrder.validate()) {
          flow.errors = purchaseOrder
          return error()
        }

        flow?.errors?.clearErrors()

        def medicineList = ni.sb.Medicine.findAllByProviderInListAndStatus(purchaseOrder.providers, true)

  			[purchaseOrder:purchaseOrder, medicineList:medicineList]
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

      on("deleteItem") {
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
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
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
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

      on("deleteItem") {
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
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
        def currentPurchaseOrderProviders = flow.purchaseOrder.providers.name
        def chosenProviders = params.list("providers")

        //update properties
        flow.purchaseOrder.properties["store", "dutyDate", "invoiceNumber", "typeOfPurchase"] = params

        if (!flow.purchaseOrder.validate()) {
          flow.errors = flow.purchaseOrder
          return error()
        }

        if (chosenProviders) {
          if (currentPurchaseOrderProviders != chosenProviders) {

            if (currentPurchaseOrderProviders.size() > chosenProviders.size()) {
              //delete providers and items from chosen provider
              def providersToRemove = currentPurchaseOrderProviders - chosenProviders

              currentPurchaseOrderProviders.each { current ->
                if (current in providersToRemove) {
                  def targetProvider = Provider.findByName(current)

                  flow.medicines.removeAll { item -> item.product.provider == targetProvider }
                  flow.products.removeAll { item -> item.product.provider == targetProvider }
                  flow.brandProductsOrders.removeAll { item -> item.product.provider == targetProvider }

                  flow.purchaseOrder.removeFromProviders targetProvider
                }
              }
            } else {
              //add new provider
              chosenProviders.findAll { !(it in currentPurchaseOrderProviders) }.each { instance ->
                flow.purchaseOrder.addToProviders Provider.findByName(instance)
              }
            }
          }
        } else {
          flash.message = "Selecciona al menos un proveedor para continuar"
        }

      }.to "editPurchaseOrder"

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
        flow.dealers = flow.providers.name + Distributor.list().name

        if (!flow.purchaseOrder) {
          response.sendError 404
        }

        flow.medicines = flow.purchaseOrder?.items?.findAll { it instanceof ni.sb.MedicineOrder }
        flow.products = flow.purchaseOrder?.items?.findAll { !(it in ni.sb.MedicineOrder) && !(it in ni.sb.BrandProductOrder) }
        flow.brandProductsOrders = flow.purchaseOrder?.items?.findAll { it instanceof ni.sb.BrandProductOrder }
      }

      on("success").to "medicine"
    }

    medicine {
      on("addItem") {
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

        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        def medicine = new MedicineOrder(params)

        if (!medicine.validate(["product", "presentation", "measure", "quantity", "purchasePrice", "sellingPrice", "bash"])) {
          medicine.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + medicine.total

        flow.medicines << medicine
      }.to "medicine"

      on("deleteItem") {
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
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
      
      on("complete") {
        this.savePurchaseOrder(flow.medicines, flow.brandProductsOrders, flow.products, flow.purchaseOrder)
      }.to "done"

      on("deleteItem") {
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
      }.to "product"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    brand {
      on("addItem") {
        if (!params?.quantity || !params?.purchasePrice || !params?.sellingPrice) { return error() }

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

        params.total = params.float("purchasePrice", 0) * params.int("quantity", 0)

        def brandProductOrder = new BrandProductOrder(params)

        if (!brandProductOrder.validate(["product", "brand", "detail", "quantity", "purchasePrice", "sellingPrice"])) {
          brandProductOrder.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        def balance = flow.purchaseOrder.balance ?: 0
        flow.purchaseOrder.balance = balance + brandProductOrder.total

        flow.brandProductsOrders << brandProductOrder
      }.to "brand"

      on("complete") {
        this.savePurchaseOrder(flow.medicines, flow.brandProductsOrders, flow.products, flow.purchaseOrder)
      }.to "done"

      on("deleteItem") {
        def index = params.int("index")
        def targetList = params?.targetList

        this.deleteItem(index, flow[targetList], flow.purchaseOrder)
      }.to "brand"

      on("medicine").to "medicine"
      on("product").to "product"
      on("brand").to "brand"
      on("editPurchaseOrder").to "editPurchaseOrder"
      on("cancel").to "done"
    }

    editPurchaseOrder {
      on("confirm") {
        def currentPurchaseOrderProviders = flow.purchaseOrder.providers.name
        def chosenProviders = params.list("providers")

        flow.purchaseOrder.properties["store", "dutyDate", "invoiceNumber", "typeOfPurchase", "status"] = params

        if (!flow.purchaseOrder.save()) {
          flow.errors = flow.purchaseOrder
          return error()
        }

        if (chosenProviders) {
          if (currentPurchaseOrderProviders != chosenProviders) {

            if (currentPurchaseOrderProviders.size() > chosenProviders.size()) {
              //delete providers and items from chosen provider
              def providersToRemove = currentPurchaseOrderProviders - chosenProviders

              currentPurchaseOrderProviders.each { current ->
                if (current in providersToRemove) {
                  def targetProvider = Provider.findByName(current)

                  flow.medicines.removeAll { item -> item.product.provider == targetProvider }
                  flow.products.removeAll { item -> item.product.provider == targetProvider }
                  flow.brandProductsOrders.removeAll { item -> item.product.provider == targetProvider }

                  flow.purchaseOrder.removeFromProviders targetProvider
                }
              }
            } else {
              //add new provider
              chosenProviders.findAll { !(it in currentPurchaseOrderProviders) }.each { instance ->
                flow.purchaseOrder.addToProviders Provider.findByName(instance)
              }
            }
          }
        } else {
          flash.message = "Selecciona al menos un proveedor para continuar"
        }

        flow?.errors?.clearErrors()
      }.to "editPurchaseOrder"

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
    //delete all items associated whit current purchase order instance
    if (purchaseOrder?.items) {
      purchaseOrder.items.clear()
    }

    //add each item instance to current purchase order
    if (medicines) {
      medicines.each { medicine ->
        //update item instance sellingPrice property
        def query = MedicineOrder.where {
          product == medicine.product && presentation == medicine.presentation && measure == medicine.measure
        }

        log.info query.updateAll(sellingPrice:medicine.sellingPrice)

        purchaseOrder.addToItems medicine
      }
    }

    if (products) {
      products.each { product ->
        def query = Item.where {
          product == product.product
        }

        log.info query.updateAll(sellingPrice:product.sellingPrice)

        purchaseOrder.addToItems product
      }
    }

    if (brandProductsOrders) {
      brandProductsOrders.each { brandProductOrder ->
        def query = BrandProductOrder.where {
          product == brandProductOrder.product && brand == brandProductOrder.brand && detail == brandProductOrder.detail
        }

        log.info query.updateAll(sellingPrice:brandProductOrder.sellingPrice)

        purchaseOrder.addToItems brandProductOrder
      }
    }

    if (!purchaseOrder.save(flush:true)) {
      purchaseOrder.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }
    }
  }

  private deleteItem(Integer index, List items, PurchaseOrder purchaseOrder) {
    purchaseOrder.balance -= items[index].total
    items.remove index
  }

  def addProduct(String productInstance, Integer providerId, String locationInstance) {
    def provider = Provider.get(providerId)
   def product = new Product(name:productInstance, location:locationInstance)

    provider.addToProducts product

    if (!provider.save(flush:true)) {
      provider.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }
    }

    render(contentType:"application/json") {
      product
    }
  }
}
