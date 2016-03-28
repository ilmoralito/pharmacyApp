package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class MeasureController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list() {
        Closure measures = {
            List<Measure> measures = Measure.list()

            measures
        }

        if (request.post) {
            Measure measure = new Measure(params)

            if (!measure.save()) {
                measure.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [measures: measures(), measure: measure]
            }
        }

        [measures: measures()]
    }

    def show(Long id) {
        Measure measure = Measure.get(id)

        if (!measure) {
            response.sendError 404
        }

        [measure: measure]
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

            flash.message = "A ocurrido un error. Intentalo otravez"

            chain action: "show", params: [id: id], model: [measure: measure]
            return
        }

        redirect action: "show", id: id
    }
}
