package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
  def grailsApplication

	static defaultAction = "productList"
	static allowedMethods = [
		productList:"GET",
    medicineList:"GET",
    brandList:"GET",
    createProduct:["GET", "POST"],
    createMedicine:["GET", "POST"],
		createBrandProduct:["GET", "POST"],
		show:"GET",
		update:"POST"
	]

  def productList(Integer providerId) {
    def provider = Provider.get providerId

    if (!provider) { response.sendError 404 }

    def products = Product.findAllByProviderAndStatus(provider, params?.status ?: true)

    [products:products, provider:provider]
  }

  def medicineList(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

  	[medicines:Medicine.findAllByProviderAndStatus(provider, params?.status ?: true), provider:provider]
  }

  def brandList(Integer providerId) {
    def provider = Provider.get providerId

    if (!provider) { response.sendError 404 }

    [brands:BrandProduct.findAllByProviderAndStatus(provider, params?.status ?: true), provider:provider]
  }

  def createProduct(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

    if (request.method == "POST") {
      params.provider = provider
      def product = new Product(params)

      if (!product.save()) {
        product.errors.allErrors.each { error ->
          log.error "[$error.field: $error.defaultMessage]"
        }

        return [product:product, providerId:providerId]
      }

      flash.message = "Producto agregado correctamente"
    }

    [providerId:providerId]
  }

  def createMedicine(Integer providerId) {
    def provider = Provider.get providerId

    if (!provider) { response.sendError 404 }

    if (request.method == "POST") {
      def presentations = grailsApplication.config.ni.sb.presentationsAndMeasures.keySet()
      def result = presentations.intersect(params.keySet())

      if (result) {
        params.provider = provider
        def medicine = new Medicine(params)

        result.each { p ->
          def presentation = new Presentation(name:p, measures:params[p])

          medicine.addToPresentations presentation
        }

        if (!medicine.save()) {
          medicine.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return [medicine:medicine, providerId:providerId]
        }

        flash.message = "Medicina agregado correctamente"
      } else {
        flash.message = "Selecciona presentaciones y unidades de medida"
      }
    }

    [providerId:providerId]
  }

  def createBrandProductFlow = {
    init {
      action {
        def provider = Provider.get params?.providerId

        if (!provider) { response.sendError 404 }

        def brands = Brand.distinctBrands.list()

        [provider:provider, brands:brands]
      }
      on("success").to "createBrandProduct"
    }

    createBrandProduct {
      on("add") {
        //check if brandProduct does not exist in chosed provider
        if (BrandProduct.findByNameAndProvider(params?.name?.capitalize(), flow.provider) || params?.name?.isEmpty()) {
          flash.message = "El producto ya esta registrado o intentas agregar un producto si nombre"
          return error()
        }

        params.provider = flow.provider
        def brandProduct = new BrandProduct(params)

        [brandProduct:brandProduct]
      }.to "brandsAndDetails"

      on("createProduct").to "createProduct"
      on("createMedicine").to "createMedicine"
      on("createBrandProduct").to "createBrandProduct"
    }

    getBrandsAndDetails {
      action {
        def brandProductBrands = flow?.brandProduct?.brands
        def availableBrands = flow.brands - brandProductBrands?.name

        [availableBrands:availableBrands, brandProductBrands:brandProductBrands]
      }

      on("success").to "brandsAndDetails"
      on(Exception).to "createBrandProduct"
    }

    brandsAndDetails {
      on("add") {
        if (params?.brand && !flow.brandProduct.brands.find { it.name == params?.brand } && params?.details) {
          def details = params?.details?.tokenize(",")
          def brand = new Brand(name:params?.brand, details:details)

          flow.brandProduct.addToBrands brand
        }
      }.to "getBrandsAndDetails"

      on("delete") {
        def brand = flow.brandProduct.brands.find {
          it.name == params?.name
        }

        flow.brandProduct.removeFromBrands brand
      }.to "getBrandsAndDetails"

      on("confirm") {
        flow.provider.addToProducts flow.brandProduct
      }.to "done"

      on("createProduct").to "createProduct"
      on("createMedicine").to "createMedicine"
      on("createBrandProduct").to "createBrandProduct"
    }

    createMedicine {
      redirect action:"createMedicine", params:[providerId:flow.provider.id]
    }

    createProduct {
      redirect action:"createProduct", params:[providerId:flow.provider.id]
    }

    done() {
      redirect action:"brandList", params:[providerId:flow.provider.id]
    }
  } 

  def show(Integer id) {
  	def product = Product.get id

  	if (!product) { response.sendError 404 }

  	[product:product, providerId:product.provider.id]
  }

  def update(Integer id) {
  	def product = Product.get id

  	if (!product) { response.sendError 404 }

  	product.properties = params

  	if (!product.save()) {
  		chain action:"show", params:[id:id], model:[product:product]
  	} else {
  		redirect action:"show", params:[id:id]
  	}
  }
}
