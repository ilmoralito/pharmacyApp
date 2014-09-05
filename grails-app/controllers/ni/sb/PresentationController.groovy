package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PresentationController {
	def grailsApplication

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
		save:"POST",
		delete:"GET"
	]

  def list(Integer productId) {
  	def product = Product.get productId

  	if (!product) { response.sendError 404 }

  	def productPresentations = product.presentations.name
  	def presentationsKeys = grailsApplication.config.ni.sb.presentationsAndMeasures.keySet()
  	def presentationsList = presentationsKeys - productPresentations
  	
  	[presentations:Presentation.findAllByProduct(product), product:product, presentationsList:presentationsList]
  }

  def save(Integer productId) {
  	def product = Product.get productId

  	if (!product) { response.sendError 404 }

  	def presentation = new Presentation(params)

  	product.addToPresentations presentation

  	product.save()

  	redirect action:"list", params:[productId:productId]
  }

  def delete(Integer id) {
  	def presentation = Presentation.get id

  	if (!presentation) { response.sendError 404 }

  	presentation.delete()

  	redirect action:"list", params:[productId:presentation.product.id]
  }
}
