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

    session["q"] = (quantity)?quantity.size():0
    session["ex"] = (expire)?expire.size():0
    session["exd"] = (expired)?expired.size():0
    session["po"] = (pendingOrders)?pendingOrders.size():0

    if (session.q > 0 || session.ex > 0 || session.exd > 0 || session.po > 0) {
      session["notif"] = "OK"
    }

    params.format = params.f

    if(params?.format && params.format != "html"){

      response.contentType = grailsApplication.config.grails.mime.types[params.format]
      response.setHeader("Content-disposition", "attachment; filename=quantity")
      List fields = ["product","product.provider.name", "quantity"]
      Map labels = ["product": "Product", "product.provider.name": "Proveedor", "quantity": "Cantidad"]

      Map parameters = [title: "Productos con baja existencia", "title.font.size": "18",
      "column.widths": [0.3, 0.3,0.1], "header.font.size": "11", "text.font.size": "11",
      "separator.color": "color.RED" ]


      exportService.export(params.format, response.outputStream,quantity, fields, labels,formatters,parameters)
    }


    [infoInstance:quantity]
  }

  def expire(){
  	def today = new Date()
    def expire = generalService.expire()

    params.format = params.f
    if(params?.format && params.format != "html"){

      response.contentType = grailsApplication.config.grails.mime.types[params.format]
      response.setHeader("Content-disposition", "attachment; filename=Productos-por-vencerse-${today.format("dd-MM-yyyy")}")
      List fields = ["product","product.provider.name","bash", "quantity", "remainingDays"]
      Map labels = ["product": "Producto", "product.provider.name": "Proveedor", "bash": "Vencimiento", "quantity": "Existencias", "remainingDays": "Dias Restantes"]

      Map parameters = [title: "Productos con fecha proxima de vencimiento", "title.font.size": "18",
      "column.widths": [0.3, 0.3, 0.2,0.1,0.2], "header.font.size": "11", "text.font.size": "11"]

      def formatDate = { MedicineOrder, bash ->
        if(bash instanceof Date){
          return new java.text.SimpleDateFormat("dd-MM-yyyy").format(bash)
        }
        return bash
      }

      Map formatters = [bash:formatDate]

      exportService.export(params.format, response.outputStream,expire, fields, labels,formatters,parameters)
    }

    [infoInstance:expire, today:today]
  }

  def expired(){
  	def today = new Date()
  	def expired = generalService.expired()

    params.format = params.f
    if(params?.format && params.format != "html"){

      response.contentType = grailsApplication.config.grails.mime.types[params.format]
      response.setHeader("Content-disposition", "attachment; filename=Productos-vencidos-${today.format("dd-MM-yyyy")}")
      List fields = ["product","product.provider.name","bash", "quantity", "daysPastDue"]
      Map labels = ["product": "Producto", "product.provider.name": "Proveedor", "bash": "Vencimiento", "quantity": "Existencias", "daysPastDue": "Dias Vencidos"]

      Map parameters = [title: "Productos con fecha proxima de vencimiento", "title.font.size": "18",
      "column.widths": [0.3, 0.3, 0.2,0.1,0.2], "header.font.size": "11", "text.font.size": "11"]

      def formatDate = { MedicineOrder, bash ->
        if(bash instanceof Date){
          return new java.text.SimpleDateFormat("dd-MM-yyyy").format(bash)
        }
        return bash
      }

      Map formatters = [bash:formatDate]

      exportService.export(params.format, response.outputStream,expired, fields, labels,formatters,parameters)
    }

    [infoInstance:expired, today:today]
  }

  def pendingOrders(){
  	def today = new Date()
  	def pendingOrders = generalService.pendingOrders()

    params.format = params.f
    if(params?.format && params.format != "html"){

      response.contentType = grailsApplication.config.grails.mime.types[params.format]
      response.setHeader("Content-disposition", "attachment; filename=Facturas-por-pagar-${today.format("dd-MM-yyyy")}")
      List fields = ["item.product.provider","dutyDate", "balance"]
      Map labels = ["Proveedor": "item.product.provider", "Fecha de pago": "dutyDate", "Total a pagar": "balance"]

      Map parameters = [title: "Productos con fecha proxima de vencimiento", "title.font.size": "18",
      "column.widths": [0.3, 0.3,0.1], "header.font.size": "11", "text.font.size": "11"]

      def formatDate = { PurchaseOrder, dutyDate ->
        if(dutyDate instanceof Date){
          return new java.text.SimpleDateFormat("dd-MM-yyyy").format(dutyDate)
        }
        return dutyDate
      }

      Map formatters = [dutyDate:formatDate]

      exportService.export(params.format, response.outputStream,pendingOrders, fields, labels,formatters,parameters)
    }

    [infoInstance:pendingOrders, today:today]
  }

}
