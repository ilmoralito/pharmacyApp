package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class BrandController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list() {
        if (request.post) {
            Brand brand = new Brand(params)

            if (!brand.save()) {
                brand.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = brand
            }

            flash.message = brand.hasErrors() ? "A ocurrido un error" : "Accion concluida correctamente"
        }

        [brands: Brand.list(), details: Detail.list()]
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

        brand.name = params

        List details = params.list("details")
        brand.details.clear()

        details.each { detail ->
            brand.addToDetails(Detail.get(detail))
        }

        if (!brand.save()) {
            brand.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = brand
        }

        flash.message = brand.hasErrors() ? "A ocurrido un error" : "Accion concluida correctamente"
        redirect action: "show", id: id
    }
}
