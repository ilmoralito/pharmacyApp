package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProviderController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        create: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list(Boolean status) {
        if (request.method == "POST") {
            Provider provider = new Provider(params)

            if (!provider.save()) {
                provider.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error"
            }
        }

        [providers: Provider.findAllByStatus(status)]
    }

    def show(Integer id) {
        Provider provider = Provider.get id

        if (!provider) {
            response.sendError 404
        }

        [provider: provider]
    }

    def update(Integer id) {
        Provider provider = Provider.get(id)

        if (!provider) {
            response.sendError 404
        }

        provider.properties = params

        if (!provider.save()) {
            render view: "show", model: [id: id, provider: provider]
            return
        }

        flash.message = "Actualizado"
        redirect action:"show", id: id
    }
}
