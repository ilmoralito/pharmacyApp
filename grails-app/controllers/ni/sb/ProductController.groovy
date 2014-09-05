package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ProductController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET"
	]

  def list(Integer providerId) {
  	def provider = Provider.get providerId

  	if (!provider) { response.sendError 404 }

  	[products:Product.findAllByProvider(provider), provider:provider]
  }
}
