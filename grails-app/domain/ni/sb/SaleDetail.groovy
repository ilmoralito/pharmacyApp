package ni.sb

class SaleDetail {
    Item item
    Integer quantity
    BigDecimal total

    Date dateCreated
    Date lastUpdated

    static constraints = {
        item nullable: false
        quantity min: 1, nullable: false
        total scale: 2, min: 0.1
    }

    static belongsTo = [sale: Sale]

    String toString() { item }
}
