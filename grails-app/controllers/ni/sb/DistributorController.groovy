package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DistributorController {
	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"]
	]

  def list() {
  	def dealers = Distributor.list()
  	[dealers:dealers]
  }
}
