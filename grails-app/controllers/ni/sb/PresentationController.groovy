package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import grails.util.Holders

@Secured(["ROLE_ADMIN"])
class PresentationController {
	def grailsApplication

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    addMeasures:"POST",
    save:"POST",
    delete:"GET"
	]

  def list(Integer productId) {
  	def medicine = Medicine.get productId

  	if (!medicine) { response.sendError 404 }

  	def productPresentations = medicine.presentations.name
    def presentationsKeys = grailsApplication.config.ni.sb.presentationsAndMeasures.keySet()
    def presentationsList = presentationsKeys - productPresentations

 	 	[presentations:Presentation.findAllByMedicine(medicine), medicine:medicine, presentationsList:presentationsList]
  }

  def addMeasures(AddMeasuresCommand cmd, Integer id, Integer productId) {
    if (cmd.hasErrors()) {
      cmd.errors.allErrors.each { error ->
        log.error "[$error.field: $error.defaultMessage]"
      }
    } else {
      def presentation = Presentation.get id

      if (!presentation) { response.sendError 404 }

      def tmp = []
      tmp.addAll presentation.measures

      tmp.each { measure ->
        presentation.removeFromMeasures measure
      }

      cmd.measures.each { measure ->
        presentation.addToMeasures measure
      }

      presentation.save()
    }

    redirect action:"list", params:[productId:productId], fragment:cmd.presentation
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

  	redirect action:"list", params:[productId:presentation.medicine.id]
  }
}

class AddMeasuresCommand {
  String presentation
  List<String> measures

  static constraints = {
    presentation inList:Holders.config.ni.sb.presentationsAndMeasures.keySet() as List
    measures nullable:false, validator:{ measures, obj ->
      if (!Holders.config.ni.sb.presentationsAndMeasures[obj.presentation].containsAll(measures)) {
        "notMatch"
      }
    }
  }
}
