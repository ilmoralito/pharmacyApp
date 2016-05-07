package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PresentationController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
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

                flash.message = "A ocurrido un error."
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

        presentation.name = params?.name

        List measures = params.list("measures")

        presentation.measures.clear()

        measures.each { measure ->
            presentation.addToMeasures(Measure.get(measure))
        }

        if (!presentation.save()) {
            presentation.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error."
            chain action: "show", params: [id: id], model: [presentation: presentation]
            return
        }

        redirect action: "show", id: id
    }
}
