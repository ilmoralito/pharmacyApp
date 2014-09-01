package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ClientController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		save:"POST",
    show:"GET",
    addTelephone:"POST",
    deletePhone:"GET"
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

  def addTelephone(Integer id, AddTelephoneCommand cmd) {
    def client = Client.get id

    if (!client) { response.sendError 404 }

    if (cmd.hasErrors()) {
      chain action:"show", params:[id:id], model:[cmd:cmd]
    } else {
      client.addToPhones cmd.phone

      client.save()

      redirect action:"show", id:id
    }
  }

  def deletePhone(Integer id, String phone) {
    def client = Client.get id

    if (!client) { response.sendError 404 }

    client.removeFromPhones phone

    redirect action:"show", id:id
  }
}

class AddTelephoneCommand {
  String phone

  static constraints = {
    phone blank:false, maxSize:8, minSize: 8, validator: { phone ->
      if (!phone.isNumber()) {
        "notMatch"
      }
    }
  }
}
