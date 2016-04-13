package ni.sb

class Sale {
    User user
    BigDecimal balance = 0.0
    Boolean canceled = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        user nullable: false
        balance scale: 2
    }

    static namedQueries = {
        fromTo { from, to ->
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime()
        }
    }

    static hasMany = [saleDetails: SaleDetail]

    String toString() { dateCreated }
}
