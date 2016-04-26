package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class NotificationsController {
    def itemService
    def purchaseOrderService

    static defaultAction = "quantity"
    static allowedMethods = [
        lowStocks: "GET",
        aboutToExpire: "GET",
        paymentDateClose: "GET",
    ]

    def lowStocks() {
        Item[] items = itemService.getItemsWithlowStocks()

        [items: items]
    }

    def aboutToExpire() {
        MedicineOrder[] medicineOrders = itemService.getMedicineOrdersAboutToExpire()

        [medicineOrders: medicineOrders]
    }

    def paymentDateClose() {
        PurchaseOrder[] purchaseOrders = purchaseOrderService.getPurchaseOrdersPaymentDateClose()

        [purchaseOrders: purchaseOrders]
    }
}
