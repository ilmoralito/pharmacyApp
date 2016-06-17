package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService {
    def configurationService

    static transactional = false

    List<Item> getItemsWithlowStocks() {
        List<Item> items = Item.where {
            quantity <= configurationService.loadConfiguration().minLowStocks
        }.list()

        items
    }

    List<MedicineOrder> getMedicineOrdersAboutToExpire() {
        Date today = new Date()
        List<MedicineOrder> medicineOrders = MedicineOrder.where {
            dueDate <= today + configurationService.loadConfiguration().minMedicineOrdersAboutToExpire
        }.list()

        medicineOrders
    }

    List<Item> getSoledItems(List<Item> items) {
        items.findAll { it.quantity != it.fixedQuantity }
    }
}
