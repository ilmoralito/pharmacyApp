package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"]
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

  	def dealers = Distributor.list()
  	[dealers:dealers]
  }
}
