package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class NotificationsController {
	static allowedMethods = [

	]

  @Secured(['ROLE_ADMIN','ROLE_USER'])
  def quantity() {
  	def prod = Item.list()

  	//def p = Item.get(2)

  	//p.properties["quantity"] = 10

  	//p = Item.get(3)
  	//p.properties["quantity"] = 3

    prod.each{
      println it.quantity
      println it.id
    }

    def c = Item.createCriteria()
    def results = c.list {
        le("quantity", 10)
    }

    [quantityInstance:results]
  }

}
