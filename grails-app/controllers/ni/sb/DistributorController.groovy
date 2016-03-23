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

                flash.message = "A ocurrido un error. Intentalo otravez"

                return [dealers: query.list(), dealer: dealer]
            }
        }

        [dealers: query.list()]
    }

    def show(Integer id) {
        Distributor dealer = Distributor.get(id)

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

        redirect action:"show", id: id
    }
}
