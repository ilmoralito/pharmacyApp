package ni.sb

class AppJob {
    def itemService
    def purchaseOrderService
    def grailsApplication

    def description = "Notifications Job"

    static triggers = {
        //simple repeatInterval: 300000 // execute job once in 5 minutes
        simple repeatInterval: 5000l
    }

    def execute() {
        //log.info "Executed notifications job"
        grailsApplication.config.ni.sb.lowStocks = itemService.getItemsWithlowStocks()?.size()
        grailsApplication.config.ni.sb.aboutToExpire = itemService.getMedicineOrdersAboutToExpire()?.size()
        grailsApplication.config.ni.sb.paymentDateClose = purchaseOrderService.getPurchaseOrdersPaymentDateClose()?.size()
    }
}