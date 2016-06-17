package ni.sb

import grails.transaction.Transactional
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Transactional
class PurchaseOrderService {
    def configurationService

    static transactional = false

    List getPurchaseOrdersPaymentDateClose() {
        List purchaseOrders = PurchaseOrder.createCriteria().list {
            le "paymentDate", new Date() + configurationService.loadConfiguration().minPurchaseOrdersPaymentDateClose
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

    BigDecimal calculateTotal(List<Item> items, String property) {
        BigDecimal total = 0.0

        items.each { item ->
            BigDecimal result = item[property] * item.fixedQuantity

            total += result
        }

        total
    }

    BigDecimal getProfits(List<Item> items) {
        BigDecimal totalPurchasePrice = calculateTotal(items, "purchasePrice")
        BigDecimal totalSellingPrice = calculateTotal(items, "sellingPrice")

        totalSellingPrice - totalPurchasePrice
    }
}
