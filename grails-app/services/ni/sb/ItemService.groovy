package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService {
    static transactional = false

    Item[] getItemsWithlowStocks() {
        Item[] items = Item.where {
            quantity <= 10
        }.list()

        items
    }

    MedicineOrder[] getMedicineOrdersAboutToExpire() {
        Date today = new Date()
        MedicineOrder[] medicineOrders = MedicineOrder.where {
            dueDate <= today + 60
        }.list()

        medicineOrders
    }
}
