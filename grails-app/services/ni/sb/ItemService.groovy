package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService {
    def configurationService

    static transactional = false

    Item[] getItemsWithlowStocks() {
        Item[] items = Item.where {
            quantity <= configuration.loadConfiguration().minLowStocks
        }.list()

        items
    }

    MedicineOrder[] getMedicineOrdersAboutToExpire() {
        Date today = new Date()
        MedicineOrder[] medicineOrders = MedicineOrder.where {
            dueDate <= today + configurationService.loadConfiguration().minMedicineOrdersAboutToExpire
        }.list()

        medicineOrders
    }
}
