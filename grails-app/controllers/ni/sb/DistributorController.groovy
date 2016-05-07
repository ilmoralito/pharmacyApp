package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
    static defaultAction = "list"
    static allowedMethods = [
        list:["GET", "POST"],
        show:"GET",
        update:"POST"
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

                flash.message = "A ocurrido un error."
                return [dealers: query.list(), dealer: dealer]
            }
        }

        [dealers: query.list()]
    }

    def show(Long id) {
        Distributor dealer = Distributor.get(id)
        List<Provider> providers = Provider.where {
            enabled == true && products.size() > 0
        }.list()

        if (!dealer) {
            response.sendError 404
        }

        [dealer: dealer, providers: providers]
    }

    def update(Long id) {
        Distributor dealer = Distributor.get(id)

        if (!dealer) {
            response.sendError 404
        }

        dealer.properties = params

        List<Integer> providers = params.list("providers")

        dealer.providers.clear()
        providers.each { providerId ->
            Provider provider = Provider.get(providerId)

            dealer.addToProviders(provider)
        }

        if (!dealer.save()) {
            dealer.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error"
        }

        redirect action:"show", id: id
    }
}
