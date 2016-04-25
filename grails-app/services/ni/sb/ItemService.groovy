package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService {
    static transactional = false

    Item[] getItemsWithlowStocks() {
        def items = Item.where {
            quantity <= 10
        }.list()

        items
    }
}
