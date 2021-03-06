package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST"
    ]

    def list() {
        List<Client> clients = Client.list()

        if (request.method == "POST") {
            Client client = new Client(params)

            if (!client.save()) {
                client.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = client
            }

            flash.message = client.hasErrors() ? "A ocurrido un error" : "Acction concluida"
        }

        [clients: clients]
    }


    def show(Integer id) {
        Client client = Client.get id

        if (!client) {
            response.sendError 404
        }

        [client: client]
    }

    def update(Integer id) {
        Client client = Client.get(id)

        if (!client) {
            response.sendError 404
        }

        client.properties = params

        if (!client.save()) {
            client.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }
        }

        flash.message = client.hasErrors() ? "A ocurrido un error" : "Acction concluida"

        redirect action: "show", id: id
    }
}
