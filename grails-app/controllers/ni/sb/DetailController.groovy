package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class DetailController {
    static defaultAction = "create"
    static allowedMethods = [
        create: "POST",
        update: "POST"
    ]

    def create() {
        Detail detail = new Detail(params)

        if (!detail.save()) {
            detail.error.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error"
        }

        redirect controller: "brand", params: [filtered: true]
    }

    def update(Long id) {
        Detail detail = Detail.get(id)

        if (!detail) {
            response.sendError 404
        }

        detail.properties["name"] = params?.name

        if (!detail.save()) {
            detail.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            render(contentType: "application/json") {
                error = true
            }
        }

        render(contentType: "application/json") {
            name = detail.name
        }
    }
}
