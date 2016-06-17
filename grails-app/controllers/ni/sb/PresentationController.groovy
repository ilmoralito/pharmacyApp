package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PresentationController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list() {
        if (request.post) {
            Presentation presentation = new Presentation(params)

            if (!presentation.save()) {
                presentation.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = presentation
            }

            flash.message = presentation.hasErrors() ? "A ocurrido un error" : "Tarea concluida"
        }

        [presentations: Presentation.list(), measures: Measure.list()]
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
        List measures = params.list("measures")

        if (!presentation) {
            response.sendError 404
        }

        presentation.name = params?.name
        presentation.measures.clear()

        measures.each { measure ->
            presentation.addToMeasures(Measure.get(measure))
        }

        if (!presentation.save()) {
            presentation.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        flash.message = presentation.hasErrors() ? "A ocurrido un error" : "Tarea concluida"
        redirect action: "show", id: id
    }
}
