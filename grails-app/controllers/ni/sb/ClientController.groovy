package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		save:"POST",
    show:"GET"
	]

  def list() {
  	[clients:Client.list()]
  }

  def save() {
  	def client = new Client(params)

  	if (!client.save()) {
      chain action:"list", model:[client:client]
    } else {
      flash.message = "Cliente creado!"
      redirect action:"list"
    }
  }

  def show(Integer id) {
    def client = Client.get id

    if (!client) { response.sendError 404 }

    [client:client]
  }

  def update(Integer id) {
    def client = Client.get id

    if (!client) { response.sendError 404 }

    client.properties = params

    if (!client.save()) {
      chain action:"show", params:[id:id], model:[client:client]
    } else {
      flash.message = "Actualizado"
      redirect action:"show", id:id
    }
  }
}
