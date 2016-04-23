package ni.sb

class Item implements Serializable {
    Product product
    Integer quantity
    Integer fixedQuantity // Define it as inmutable
    BigDecimal purchasePrice
    BigDecimal sellingPrice

    Date dateCreated
    Date lastUpdated

    static constraints = {
        quantity min: 1
        fixedQuantity min: 1
        purchasePrice min: 0.1, scale: 2
        sellingPrice min: 0.1, scale: 2, validator: { sellingPrice, item ->
            if (sellingPrice < item.purchasePrice) {
                "notValid"
            }
        }
    }

    static belongsTo = [purchaseOrder: PurchaseOrder]

    String toString() { product.name }
}
