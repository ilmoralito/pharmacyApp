package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
    static defaultAction = "list"
    static allowedMethods = [
        list:"GET",
        create: ["GET", "POST"],
        show:"GET",
        update:"POST"
    ]

    def list() {
        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        List<Distributor> dealers = Distributor.where {
            enabled == enabled
        }.list()

        [dealers: dealers]
    }

    def create() {
        Distributor distribuidor = new Distributor(params)

        if (request.method == "POST") {
            if (!distribuidor.save()) {
                distribuidor.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = distribuidor
            }

            flash.message = distribuidor.hasErrors() ? "A ocurrido un error" : "Accion concluida correctamente"
        }

        [distribuidor: distribuidor]
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
