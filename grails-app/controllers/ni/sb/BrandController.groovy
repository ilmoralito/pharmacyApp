package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class BrandController {
	static defaultAction = "list"
	static allowedMethods = [ list:"GET" ]

  def list(BrandProduct id) {
  	def brands = Brand.findAllByBrandProduct id

  	if (!brands) { response.sendError 404 }

//  	[brands:brands]
	println brands
  }
}
