package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		save:"POST"
	]

  def list() {
  	[clients:Client.list()]
  }

  def save() {
  	def client = new Client(params)

  	flash.message = client.save() ? "Cliente creado" : "A courrido un error verifica que los datos sean correctos"
  	redirect action:"list"
  }
}
