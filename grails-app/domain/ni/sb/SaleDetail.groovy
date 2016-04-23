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

    static namedQueries = {
        fromTo { Date from, Date to ->
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }

        bySaleUser { user ->
            sale {
                eq "user", user
            }
        }
    }

    def beforeValidate() {
        total = item.sellingPrice * quantity
    }

    String toString() { item }
}
