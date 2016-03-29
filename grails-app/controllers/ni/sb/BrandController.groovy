package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class BrandController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"]
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
}
