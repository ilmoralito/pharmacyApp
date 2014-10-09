package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class NotificationsController {
	static allowedMethods = [
    quantity:"GET",
    expire:"GET",
    expired:"GET",
    pendingOrders:"GET"
	]

  def generalService

  @Secured(['ROLE_ADMIN','ROLE_USER'])
  def quantity() {
    def quantity = generalService.quantity()
    def expire = generalService.expire()
    def expired = generalService.expired()
    def pendingOrders = generalService.pendingOrders()

    session["q"] = quantity.size()
    session["ex"] = expire.size()
    session["exd"] = expired.size()
    session["po"] = pendingOrders.size()

    if (quantity.size() > 0 || expire.size() > 0 || expired.seze() > 0 || pendingOrders.size() > 0) {
      session["notif"] = "OK"
    }
    [infoInstance:quantity]
  }

  def expire(){
  	def today = new Date()
    def expire = generalService.expire()
    [infoInstance:expire, today:today]
  }

  def expired(){
  	def today = new Date()
  	def expired = generalService.expired()
    [infoInstance:expired, today:today]
  }

  def pendingOrders(){
  	def today = new Date()
  	def pendingOrders = generalService.pendingOrders()
    [infoInstance:pendingOrders, today:today]
  }

}
