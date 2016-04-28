package ni.sb

class AppJob {
    def itemService
    def purchaseOrderService

    def description = "Notifications Job"

    static triggers = {
        //simple repeatInterval: 300000 // execute job once in 5 minutes
        simple repeatInterval: 5000l
    }

    def execute() {
        /*
        session?.lowStocks = itemService.getItemsWithlowStocks().size()
        session?.aboutToExpire = itemService.getMedicineOrdersAboutToExpire().size()
        session?.paymentDateClose = purchaseOrderService.getPurchaseOrdersPaymentDateClose().size()
        */
    }
}
