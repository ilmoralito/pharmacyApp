package ni.sb

class AppJob {
    def grailsApplication
    def itemService
    def purchaseOrderService

    def description = "Notifications Job"

    static triggers = {
        simple name: "notifications", startDelay: 60000, repeatInterval:  300000
    }

    def execute() {
        ConfigObject config = grailsApplication.config.ni.sb

        config.lowStocks = itemService.getItemsWithlowStocks()?.size()
        config.aboutToExpire = itemService.getMedicineOrdersAboutToExpire()?.size()
        config.paymentDateClose = purchaseOrderService.getPurchaseOrdersPaymentDateClose()?.size()
    }
}
