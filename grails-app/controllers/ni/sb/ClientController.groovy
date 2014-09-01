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

  	if (!client.save()) {
      chain action:"list", model:[client:client]
    } else {
      flash.message = "Cliente creado!"
      redirect action:"list"
    }
  }
}
