package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        removeTelephone: "GET"
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

        if (!client) {
            response.sendError 404
        }

        List<String> telephones = params.list("telephones")?.findAll { it != "" }

        client.fullName = params.fullName
        client.address = params.address
        client.identificationCard = params.identificationCard
        client.status = params.boolean("status")

        if (!client.save()) {
            client.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Intentalo otravez"
        }

        redirect action: "show", id: id
    }

    def removeTelephone(Long clientId, Long id) {
        Client client = Client.get clientId
        Telephone telephone = Telephone.get id

        if (!client || !telephone) {
            response.sendError 404
        }

        client.removeFromTelephones telephone
        client.save()

        redirect action: "show", id: clientId
    }
}
