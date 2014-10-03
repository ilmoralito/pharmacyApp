package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class NotificationsController {
	static allowedMethods = [

	]

  @Secured(['ROLE_ADMIN','ROLE_USER'])
  def quantity() {
  	def c = Item.createCriteria()
    def results = c.list {
        le("quantity", 10)
    }

    [infoInstance:results, notif:results.size(), q:results.size()]
  }

  def expire(){
  	def today = new Date()
  	def proyectionDate = new Date() + 90

   	def c = MedicineOrder.createCriteria()
    def results = c.list {
        between("bash",today, proyectionDate)
        order("bash", "desc")
    }
    [infoInstance:results, notif:results.size(), today:today, ex:results.size()]
  }

  def expired(){
  	def today = new Date()
  	def date = new Date() - 1

  	def c = MedicineOrder.createCriteria()
    def results = c.list {
        le("bash",date)
        order("bash", "desc")
    }
    [infoInstance:results, notif:results.size(), today:today, exd:results.size()]
  }

  def pendingOrders(){
  	def today = new Date()
  	def date = new Date() + 7

  	def c = PurchaseOrder.createCriteria()
    def results = c.list {
        le("dutyDate",date)
        order("dutyDate", "desc")
    }
    println results
    [infoInstance:results, notif:results.size(), today:today, pen:results.size()]
  }

}
