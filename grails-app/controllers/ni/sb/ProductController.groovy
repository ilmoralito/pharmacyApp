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

    def productList(Long providerId) {
        Provider provider = Provider.get(providerId)

        if (!provider) {
            response.sendError 404
        }

        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        def products = Product.where {
            provider == provider && enabled == enabled
        }

        if (request.method == "POST") {
            Product product = new Product(params)

            provider.addToProducts(product)

            if (!product.save()) {
                product.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = product
            }

            flash.message = product.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        }

        [
            products: products.list().findAll {!(it instanceof Medicine) && !(it instanceof BrandProduct)},
            provider: provider
        ]
    }

    def medicineList(Long providerId) {
        Provider provider = Provider.get(providerId)

        if (!provider) {
            response.sendError 404
        }

        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        def medicines = Medicine.where {
            provider == provider && enabled == enabled
        }

        if (request.method == "POST") {
            Medicine medicine = new Medicine(params)

            provider.addToProducts(medicine)

            if (!medicine.save()) {
                medicine.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = medicine
            }

            flash.message = medicine.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        }

        [medicines: medicines.list(), provider: provider]
    }

    def brandProductList(Long providerId) {
        Provider provider = Provider.get(providerId)

        if (!provider) {
            response.sendError 404
        }

        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        def brandProducts = BrandProduct.where {
            provider == provider && enabled == enabled
        }

        if (request.post) {
            BrandProduct brandProduct = new BrandProduct(params)

            provider.addToProducts(brandProduct)

            if (!brandProduct.save()) {
                brandProduct.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.bag = brandProduct
            }

            flash.message = brandProduct.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        }

        [brandProducts: brandProducts.list(), provider: provider]
    }

    def show(Long id) {
        def product = Product.get(id)

        if (!product) {
            response.sendError 404
        }

        [product: product, providerId: product.provider.id]
    }

    def update(Long id) {
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
            product.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = product
        }

        flash.message = product.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        redirect action: "show", id: id
    }
}
