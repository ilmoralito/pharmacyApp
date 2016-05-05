package ni.sb

import groovy.transform.ToString

@ToString
class Configuration {
    def distributorService

    Integer minLowStocks
    Integer minMedicineOrdersAboutToExpire
    Integer minPurchaseOrdersPaymentDateClose

    Date dateCreated
    Date lastUpdated

    static constraints = {
        minLowStocks min: 1
        minMedicineOrdersAboutToExpire min: 1
        minPurchaseOrdersPaymentDateClose min: 1, validator: { prop, obj ->
            List<Distributor> distributors = obj.distributorService.getValidDistributors()

            if (distributors) {
                prop <= obj.distributorService.getMaximumDistributorDaysToPay(distributors)
            }
        }
    }
}
