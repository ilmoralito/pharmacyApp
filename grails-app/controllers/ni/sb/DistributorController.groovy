package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"],
    show:"GET",
    update:"POST"
	]

  def list() {
  	if (request.method == "POST") {
  		def distributor = new Distributor(
        name:params?.name,
        telephones:params.subMap(["convencional", "movistar", "claro"]).findAll { it.value }
      )

  		if (!distributor.save()) {
  			distributor.errors.allErrors.each { error ->
  				log.error "[$error.field: $error.defaultMessage]"
  			}
  		}
  	}

  	def dealers = Distributor.list([sort:"id", order:"desc"])
  	[dealers:dealers]
  }

  def show(Integer id) {
    def dealer = Distributor.get id

    if (!dealer) { response.sendError 404 }

    [dealer:dealer]
  }

  def update(Integer id) {
    def dealer = Distributor.get id

    if (!dealer) { response.sendError 404 }

    dealer.name = params?.name

    if (!params.convencional) {
      dealer.telephones.remove("convencional")
    } else {
      dealer.telephones.convencional = params.convencional
    }

    if (!params.movistar) {
      dealer.telephones.remove("movistar")
    } else {
      dealer.telephones.movistar = params.movistar
    }

    if (!params.claro) {
      dealer.telephones.remove("claro")
    } else {
      dealer.telephones.claro = params.claro
    }

    if (!dealer.save()) {
      dealer.errors.allErrors.each { e ->
        log.error "[$e.field: $e.defaultMessage]"
      }

      flash.message = "Datos incorrectos"
    }

    flash.message = "Datos actualizados"

    redirect action:"show", id:id
  }
}
