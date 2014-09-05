package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		save:"POST"
	]

  def list(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

  	[products:Product.findAllByProvider(provider), provider:provider]
  }

  def save(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

  	def product = new Product(name:params?.name, code:params?.code, provider:provider)

  	if (!product.save()) {
  		product.errors.allErrors.each { error ->
  			log.error "[$error.field: $error.defaultMessage]"
  		}
  	}

  	redirect action:"list", params:[providerId:providerId]
  }
}
