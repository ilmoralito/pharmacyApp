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
  def exportService
  def grailsApplication  //inject GrailsApplication

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
    params.format = params.f

    if(params?.format && params.format != "html"){ 

      response.contentType = grailsApplication.config.grails.mime.types[params.format] 
      response.setHeader("Content-disposition", "attachment; filename=expire")
      List fields = ["product","product.provider.name","bash", "quantity"]
      Map labels = ["product": "Product", "product.provider.name": "Proveedor", "bash": "Vencimiento", "quantity": "Cantidad"]

      Map parameters = [title: "Productos con fecha proxima de vencimiento", "title.font.size": "18",  "column.widths": [0.3, 0.3, 0.2,0.1]]

      exportService.export(params.format, response.outputStream,expire, fields, labels,[:],parameters) 
    }

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
