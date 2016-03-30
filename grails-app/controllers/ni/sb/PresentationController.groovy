package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PresentationController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        addRemoveMeasure: "POST"
    ]

    def list() {
        Closure presentations = {
            Presentation.list()
        }

        Closure measures = {
            Measure.list()
        }

        if (request.post) {
            Presentation presentation = new Presentation(params)

            if (!presentation.save()) {
                presentation.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [presentations: presentations(), presentation: presentation, measures: measures()]
            }
        }

        [presentations: presentations(), measures: measures()]
    }

    def show(Long id) {
        Presentation presentation = Presentation.get(id)

        if (!presentation) {
            response.sendError 404
        }

        [presentation: presentation]
    }

    def update(Long id) {
        Presentation presentation = Presentation.get(id)

        if (!presentation) {
            response.sendError 404
        }

        presentation.properties = params

        if (!presentation.save()) {
            presentation.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"
            chain action: "show", params: [id: id], model: [presentation: presentation]
            return
        }

        redirect action: "show", id: id
    }

    def addRemoveMeasure(Long id) {
        Presentation presentation = Presentation.get(id)

        if (!presentation) {
            response.sendError 404
        }

        List measures = params.list("measures")

        presentation.measures.clear()

        measures.each { measure ->
            presentation.addToMeasures(Measure.get(measure))
            presentation.save()
        }

        redirect action: "show", id: id
    }
}
