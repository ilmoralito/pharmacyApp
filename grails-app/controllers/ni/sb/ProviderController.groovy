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

  def createFlow = {
    init {
      action {
        flow.products = []
      }

      on("success").to "createProduct"
    }

    createProduct {
      on("confirm") { addProviderCommand cmd ->
        if (cmd.hasErrors()) {
          flow.errors = cmd

          return error()
        }

        [name:cmd.name, address:cmd.address, phone:cmd.phone]
      }.to "addProducts"
    }

    addProducts {
      on("confirm") {
        def provider = new Provider(
          name:flow.name,
          address:flow.address,
          phone:flow.phone,
          products:flow.products
        )

        if (!provider.save(flush:true)) {
          provider.errors.allErrors.each { error -> log.error "[$error.field: $error.defaultMessage]" }

          return error()
        }
      }.to "done"

      on("addProduct") { AddProductCommand cmd ->
        if (cmd.hasErrors()) {
          flow.errors = cmd

          return error()
        }

        flow.products << cmd.product
      }.to "addProducts"

      on("removeProduct") {
        flow.products -= params?.product
      }.to "addProducts"
    }

    done() {
      redirect action:"list"
    }
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

  static constraints = {
    product blank:false
  }
}
