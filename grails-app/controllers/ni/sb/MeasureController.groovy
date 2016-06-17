package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class MeasureController {
    static defaultAction = "create"
    static allowedMethods = [
        create: "POST",
        update: "POST"
    ]

    def create() {
        Measure measure = new Measure(params)

        if (!measure.save()) {
            measure.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        flash.message = measure.hasErrors() ? "A ocurrido un error" : "Tarea concluida"
        redirect controller: "presentation", params: params
    }

    def update(Long id) {
        Measure measure = Measure.get(id)

        if (!measure) {
            response.sendError 404
        }

        measure.name = params.name

        if (!measure.save()) {
            measure.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        render(contentType: "application/json") {
            if (measure.hasErrors()) {
                error = true
            } else {
                name = measure.name
            }
        }
    }
}
