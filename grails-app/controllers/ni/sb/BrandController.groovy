package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class BrandController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list() {
        Closure brands = {
            Brand.list()
        }

        Closure details = {
            Detail.list()
        }

        if (request.post) {
            Brand brand = new Brand(params)

            if (!brand.save()) {
                brand.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error"

                return [brands: brands(), details: details(), brand: brand]
            }
        }

        [brands: brands(), details: details()]
    }

    def show(Long id) {
        Brand brand = Brand.get(id)

        if (!brand) {
            response.sendError 404
        }

        [brand: brand]
    }

    def update(Long id) {
        Brand brand = Brand.get(id)

        if (!brand) {
            response.sendError 404
        }

        brand.properties["name"] = params

        if (!brand.save()) {
            brand.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error"

            chain action: "show", params: [id: id], model: [brand: brand]
            return
        }

        // Update details
        List details = params.list("details")

        brand.details.clear()

        details.each { detail ->
            brand.addToDetails(Detail.get(detail))
            brand.save(flush: true)
        }

        redirect action: "show", id: id
    }
}
