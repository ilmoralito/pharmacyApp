package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
    def grailsApplication

    static defaultAction = "productList"
    static allowedMethods = [
        productList: ["GET", "POST"],
        medicineList: ["GET", "POST"],
        brandList: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def productList(Long providerId, Boolean enabled, Boolean filtered) {
        Provider provider = Provider.get(providerId)

        if (!provider) {
            response.sendError 404
        }

        Closure getProducts = {
            if (enabled == null) {
                enabled = true
            }

            Product.findAllByProviderAndEnabled(provider, enabled)
        }

        if (request.method == "POST") {
            params.enabled = true
            Product product = new Product(params)

            provider.addToProducts(product)

            if (!product.save()) {
                product.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [products: getProducts(), provider: provider, product: product]
            }
        }

        [products: getProducts(), provider: provider]
    }

  def medicineList(Integer providerId, Boolean enabled, Boolean filtered) {
    Provider provider = Provider.get(providerId)

    if (!provider) {
        response.sendError 404
    }

    Closure getMedicines = {
        if (enabled == null) {
            enabled = true
        }

        Medicine.findAllByProviderAndEnabled(provider, enabled)
    }

    if (request.method == "POST") {
        Medicine medicine = new Medicine(params)

        provider.addToProducts(medicine)

        if (!medicine.save()) {
            provider.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"

            return [
                medicines: getMedicines(),
                provider: provider,
                medicine: medicine
            ]
        }
    }

    [medicines: getMedicines(), provider:provider]
  }

  def brandList(Integer providerId) {
    def provider = Provider.get providerId

    if (!provider) { response.sendError 404 }

    [brands:BrandProduct.findAllByProviderAndStatus(provider, params?.enabled ?: true), provider:provider]
  }

    def show(Integer id) {
        def product = Product.get(id)

        if (!product) {
            response.sendError 404
        }

        [product: product, providerId: product.provider.id]
    }

    def update(Integer id) {
        def product = Product.get(id)

        if (!product) {
            response.sendError 404
        }

        product.properties["name", "code", "genericName"] = params

        if (product instanceof Medicine) {
            /*
            product.presentations.clear()

            */
            List tempPresentations = []
            tempPresentations.addAll(product.presentations)

            tempPresentations.each { p ->
                product.removeFromPresentations(p)
            }

            List<Integer> presentations = params.list("presentations")
            presentations.each { presentation ->
                product.addToPresentations(Presentation.get(presentation))
            }
        }

        if (!product.save()) {
            chain action: "show", params: [id: id], model: [product: product]
        } else {
            redirect action: "show", params: [id: id]
        }
    }
}
