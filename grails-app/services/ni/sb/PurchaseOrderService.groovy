package ni.sb

import grails.transaction.Transactional
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Transactional
class PurchaseOrderService {
    static transactional = false

    List getPurchaseOrdersPaymentDateClose() {
        List purchaseOrders = PurchaseOrder.createCriteria().list {
            le "paymentDate", new Date() + 10
            eq "paymentType", "credit"
            eq "paymentStatus", "pending"

            projections {
                property "invoiceNumber", "invoiceNumber"
                property "distributor", "distributor"
                property "paymentDate", "paymentDate"
            }

            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        purchaseOrders
    }
}
