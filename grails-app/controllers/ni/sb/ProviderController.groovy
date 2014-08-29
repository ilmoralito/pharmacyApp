package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProviderController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		show:"GET",
    update:"POST",
    addProduct:"POST",
    removeProduct:"GET"
	]

  def list() {
  	[providers:Provider.list()]
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

  def addProduct(Integer id, AddProductCommand cmd) {
    def provider = Provider.get id
    
    if (!provider) { response.sendError 404 }

    if (cmd.hasErrors()) {
      render view:"show", model:[id:id, cmd:cmd, provider:provider]
      return
    }

    provider.addToProducts cmd.product
    provider.save()

    redirect action:"show", id:id
  }

  def removeProduct(Integer providerId, String product) {
    def provider = Provider.get providerId
    
    if (!provider) { response.sendError 404 }

    provider.products -= product

    redirect action:"show", id:providerId
  }
}

class AddProductCommand {
  String product

  static constraints = {
    product blank:false
  }
}
