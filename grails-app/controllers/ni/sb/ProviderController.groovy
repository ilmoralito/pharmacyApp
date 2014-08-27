package ni.sb

import grails.plugin.springsecurity.annotation.Secured

class ProviderController {
	static defaultAction = "list"
	static allowedMethods = [
		list:"GET"
	]

  def list() {
  	
  }
}
