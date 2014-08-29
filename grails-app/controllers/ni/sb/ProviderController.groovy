package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProviderController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		show:"GET",
    update:"POST"
	]

  def list() {
  	[providers:Provider.list()]
  }

  def show(Integer id) {
  	def provider = Provider.get id

  	if (!provider) { response.sendError 404 }

  	[provider:provider, products:provider.products]
  }

  def update(Integer id) {
    def provider = Provider.get(id)

    if (!provider) { response.sendError 404 }

    provider.properties = params

    if (!provider.save()) {
      render view:"show", model:[id:id, provider:provider, products:provider.products]
      return
    }

    flash.message = "Actualizado"
    redirect action:"show", id:id
  }
}
