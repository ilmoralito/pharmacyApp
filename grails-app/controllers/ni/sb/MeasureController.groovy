package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class MeasureController {
    static defaultAction = "create"
    static allowedMethods = [
        create: "POST",
        update: "POST"
    ]

    def create() {
        Measure measure = new Measure(params)

        if (!measure.save()) {
            measure.error.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error"
        }

        redirect controller: "presentation", params: [filtered: true]
    }

    def update(Long id) {
        Measure measure = Measure.get(id)

        if (!measure) {
            response.sendError 404
        }

        measure.properties = params

        if (!measure.save()) {
            measure.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            render(contentType: "application/json") {
                error = true
            }
        }

        render(contentType: "application/json") {
            name = measure.name
        }
    }
}
