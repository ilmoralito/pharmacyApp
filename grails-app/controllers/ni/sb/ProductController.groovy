package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    createProduct:["GET", "POST"],
    createMedicine:["GET", "POST"],
		createBrandProduct:["GET", "POST"],
		show:"GET",
		update:"POST"
	]

  def list(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

  	[products:Product.findAllByProviderAndStatus(provider, params?.status ?: true), provider:provider]
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
      params.provider = provider
      def medicine = new Medicine(params)

      if (!medicine.save()) {
        medicine.errors.allErrors.each { error ->
          log.error "[$error.field: $error.defaultMessage]"
        }

        return [medicine:medicine, providerId:providerId]
      }

      flash.message = "Medicina agregado correctamente"
    }

    [providerId:providerId]
  }

  def createBrandProduct(Integer providerId) {
    def provider = Provider.get providerId

    if (!provider) { response.sendError 404 }

    if (request.method == "POST") {
      params.provider = provider
      def brand = new BrandProduct(params)

      if (!brand.save()) {
        brand.errors.allErrors.each { error ->
          log.error "[$error.field: $error.defaultMessage]"
        }

        return [brand:brand, providerId:providerId]
      }

      flash.message = "Producto agregado correctamente"
    }

    [providerId:providerId]
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
