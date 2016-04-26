package ni.sb

import grails.transaction.Transactional

@Transactional
class PurchaseOrderService {
    static transactional = false

    PurchaseOrder[] getPurchaseOrdersPaymentDateClose() {
        Date today = new Date()
        PurchaseOrder[] purchaseOrders = PurchaseOrder.where {
            paymentDate <= today + 30
        }.list()

        purchaseOrders
    }
}
