package ni.sb

class SaleDetail implements Serializable {
    Item item
    Integer quantity
    BigDecimal total

    Date dateCreated
    Date lastUpdated

    static constraints = {
        quantity min: 1
        total min: 0.1, scale: 2
    }

    static belongsTo = [sale: Sale]

    def beforeValidate() {
        total = item.sellingPrice * quantity
    }

    String toString() { item }
}
