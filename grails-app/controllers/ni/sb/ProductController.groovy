package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
    def grailsApplication

    static defaultAction = "productList"
    static allowedMethods = [
        productList: ["GET", "POST"],
        medicineList: ["GET", "POST"],
        brandProductList: ["GET", "POST"],
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
                medicine.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [
                    medicines: getMedicines(),
                    provider: provider,
                    product: medicine
                ]
            }
        }

        [medicines: getMedicines(), provider: provider]
    }

    def brandProductList(Integer providerId, Boolean enabled, Boolean filtered) {
        Provider provider = Provider.get(providerId)

        if (!provider) {
            response.sendError 404
        }

        Closure getBrandProducts = {
            if (enabled == null) {
                enabled = true
            }

            BrandProduct.findAllByProviderAndEnabled(provider, enabled)
        }

        if (request.post) {
            BrandProduct brandProduct = new BrandProduct(params)

            provider.addToProducts(brandProduct)

            if (!brandProduct.save()) {
                brandProduct.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [
                    brandProducts: getBrandProducts(),
                    provider: provider,
                    product: brandProduct
                ]
            }
        }

        [brandProducts: getBrandProducts(), provider: provider]
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

        product.properties = params

        if (product instanceof Medicine) {
            product.presentations.clear()

            List<Integer> presentations = params.list("presentations")

            presentations.each { presentation ->
                product.addToPresentations(Presentation.get(presentation))
            }
        }

        if (product instanceof BrandProduct) {
            product.brands.clear()

            List<Integer> brands = params.list("brands")

            brands.each { brandId ->
                product.addToBrands(Brand.get(brandId))
            }
        }

        if (!product.save()) {
            chain action: "show", params: [id: id], model: [product: product]
        } else {
            redirect action: "show", params: [id: id]
        }
    }
}
