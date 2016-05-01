package ni.sb

class Sale implements Serializable {
    User user
    BigDecimal balance
    Client client
    BigDecimal moneyReceived
    String annotation
    Employee employee
    Boolean canceled = false
    String reazonOfCanelation
    Date dateOfCancelation

    Date dateCreated
    Date lastUpdated

    static constraints = {
        balance min: 1.0, scale: 2
        moneyReceived min: 1.0, scale: 2, validator: { moneyReceived, sale ->
            moneyReceived >= sale.balance
        }
        annotation nullable: true, maxSize: 255
        employee nullable: true
        reazonOfCanelation nullable: true, maxSize: 255, validator: { reazonOfCanelation, obj ->
            if (obj.canceled) {
                return reazonOfCanelation != ""
            }
        }
        dateOfCancelation nullable: true
    }

    static namedQueries = {
        fromTo { from, to ->
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }
    }

    List<SaleDetail> saleDetails
    static hasMany = [saleDetails: SaleDetail]

    def beforeUpdate() {
        if (isDirty("canceled")) {
            dateOfCancelation = new Date()
        }
    }

    String toString() { "by $toName for $user.fullName" }
}
