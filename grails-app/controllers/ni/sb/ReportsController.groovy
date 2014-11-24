package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ReportsController {
	static defaultAction = "sales"
	static allowedMethods = [
		sales:["GET", "POST"]
	]

  def sales() {
  	def from = params.date("from", "yyyy-MM-dd") ?: new Date()
  	def to = params.date("to", "yyyy-MM-dd") ?: new Date()

  	
  }
}
