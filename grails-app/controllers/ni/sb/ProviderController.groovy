package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProviderController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    create:["GET", "POST"],
		show:"GET",
    update:"POST",
    delete:"GET",
    addProduct:"POST",
    removeProduct:"GET"
	]

  def list() {
  	[providers:Provider.list()]
  }

  def create() {
    def provider = new Provider(params)

    if (request.method == "POST") {
      if (!provider.save()) {
        return [provider:provider]
      }

      flash.message = "Proveedor creado"
    } else {
      [provider:provider]
    }
  }

  def show(Integer id) {
  	def provider = Provider.get id

  	if (!provider) { response.sendError 404 }

  	[provider:provider]
  }

  def update(Integer id) {
    def provider = Provider.get(id)

    if (!provider) { response.sendError 404 }

    provider.properties = params

    if (!provider.save()) {
      render view:"show", model:[id:id, provider:provider]
      return
    }

    flash.message = "Actualizado"
    redirect action:"show", id:id
  }

  def addProduct(Integer id) {
    def provider = Provider.get id
    
    if (!provider) { response.sendError 404 }

    if (cmd.hasErrors()) {
      render view:"show", model:[id:id, cmd:cmd, provider:provider]
      return
    }

    provider.addToProducts cmd.product

    if (!provider.save()) {
      //pro
    }

    redirect action:"show", id:id
  }

  def removeProduct(Integer providerId, String product) {
    def provider = Provider.get providerId
    
    if (!provider) { response.sendError 404 }

    provider.removeFromProducts product

    if (!provider.save()) {
      chain action:"show", id:providerId, model:[cmd:provider]
      return
    }

    redirect action:"show", id:providerId
  }

  def delete(Integer id) {
    def provider = Provider.get id

    if (!provider) { response.sendError 404 }

    try {
      provider.delete()
    }
    catch(org.springframework.dao.DataIntegrityViolationException e) {
      flash.message "Error al intentar eliminar el proveedor $e"
    }
    
    redirect action:"list"
  }

}

class addProviderCommand implements Serializable {
  String name
  String address
  String phone

  static constraints = {
    importFrom Provider
  }
}

class AddProductCommand implements Serializable {
  String product
  String code

  static constraints = {
    importFrom Product
  }
}
