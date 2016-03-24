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

    def list(Boolean enabled, Boolean filtered) {
        if (request.method == "POST") {
            Provider provider = new Provider(params)

            if (!provider.save()) {
                provider.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [providers: Provider.findAllByEnabled(enabled), provider: provider]
            }
        }

        [providers: Provider.findAllByEnabled(enabled)]
    }

    def show(Integer id) {
        Provider provider = Provider.get(id)

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

        // If provider enabled property is set to false
        // then remove provider from Distributors that
        // has current provider
        Closure updateDistributors = {
            List<Distributor> dealers = []

            if (provider.enabled == false) {
                dealers = Distributor.list().findAll { 
                    it.providers.contains(provider)
                }

                dealers.each { dealer ->
                    dealer.removeFromProviders(provider)

                    dealer.save()
                }
            }

            dealers?.size()
        }

        Integer distributorsAffected = updateDistributors()
        flash.message = distributorsAffected ? "$distributorsAffected distribuidores afectados" : "Actualizacion correcta"

        redirect action:"show", id: id
    }
}
