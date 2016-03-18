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

    def list() {
        if (request.method == "POST") {
            def distributor = new Distributor(
                name:params.name,
                telephones:params.subMap(["convencional", "movistar", "claro"]).findAll { it.value }
            )

            if (!distributor.save()) {
                distributor.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }
            }
        }

        List<Distributor> dealers = Distributor.list()

        [dealers:dealers]
    }

    def show(Integer id) {
        Distributor dealer = Distributor.get id

        if (!dealer) {
            response.sendError 404
        }

        [dealer: dealer]
    }

    def update(Integer id) {
        Distributor dealer = Distributor.get id

        if (!dealer) {
            response.sendError 404
        }

        dealer.properties = params
        dealer.save()

        redirect action:"show", id:id
    }
}
