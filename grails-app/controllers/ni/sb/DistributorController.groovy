package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
    static defaultAction = "list"
    static allowedMethods = [
        list:["GET", "POST"],
        show:"GET",
        update:"POST",
        addRemoveProvider: "POST"
    ]

    def list(Boolean enabled, Boolean filtered) {
        def query = Distributor.where {
            enabled == enabled
        }

        if (request.method == "POST") {
            Distributor dealer = new Distributor(params)

            if (!dealer.save()) {
                dealer.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [dealers: query.list(), dealer: dealer]
            }
        }

        [dealers: query.list()]
    }

    def show(Integer id) {
        Distributor dealer = Distributor.get(id)
        List<Provider> providers = Provider.list()

        if (!dealer) {
            response.sendError 404
        }

        [dealer: dealer, providers: providers]
    }

    def update(Integer id) {
        Distributor dealer = Distributor.get id

        if (!dealer) {
            response.sendError 404
        }

        dealer.properties = params
        dealer.save()

        redirect action:"show", id: id
    }

    def addRemoveProvider(Long id) {
        Distributor dealer = Distributor.get(id)

        if (!dealer) {
            response.sendError 404
        }

        // Remove all providers from dealer
        dealer.providers.clear()

        // Add new provider
        List<Integer> providers = params.list("providers")

        providers.each { providerId ->
            Provider provider = Provider.get(providerId)

            dealer.addToProviders(provider)
        }

        if (!dealer.save()) {
            dealer.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"
        } else {
            flash.message = "Laboratorios agregados correctamente"
        }

        redirect action: "show", id: id
    }
}
