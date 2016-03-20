package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
    ]

    def list() {
        Boolean status = params?.status ?: true

        if (request.method == "POST") {
            List telephones = params.list("telephones")?.findAll { it != "" }
            Client client = new Client(
                fullName: params.fullName,
                address: params.address,
                identificationCard: params.identificationCard
            )

            telephones.each { telephone ->
                client.addToTelephones(new Telephone(telephoneNumber: telephone))
            }

            if (!client.save()) {
                client.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"
            }
        }

        [clients: Client.findAllByStatus(status)]
    }


    def show(Integer id) {
        Client client = Client.get id

        if (!client) {
            response.sendError 404
        }

        [client: client]
    }

    def update(Integer id) {
        Client client = Client.get id
        List telephones = params.list("telephones")?.findAll { it != "" }

        if (!client) {
            response.sendError 404
        }

        client.fullName = params.fullName
        client.address = params.address
        client.identificationCard = params.identificationCard

        List telephonesTmp = []
        telephonesTmp = client.telephones

        telephonesTmp.each { t ->
            client.removeFromTelephones(Telephone.findByTelephoneNumber(t.telephoneNumber))
        }

        //log.info "$affectedTelephones deleted"

        telephones.each { telephone ->
            Telephone telephoneInstance = new Telephone(telephoneNumber: telephone)

            client.addToTelephones telephoneInstance
        }

        redirect action: "show", id: id
    }
}
