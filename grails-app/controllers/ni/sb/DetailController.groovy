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
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.message = detail.hasErrors() ? "A ocurrido un error" : "Accion concluida correctamente"
        }

        redirect controller: "brand", params: [tab: "filter"]
    }

    def update(Long id) {
        Detail detail = Detail.get(id)

        if (!detail) {
            response.sendError 404
        }

        detail.name = params.name

        if (!detail.save()) {
            detail.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        render(contentType: "application/json") {
            if (detail.hasErrors()) {
                error = true
            } else {
                name = detail.name
            }
        }
    }
}
