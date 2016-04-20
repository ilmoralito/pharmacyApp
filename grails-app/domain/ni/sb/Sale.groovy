package ni.sb

class Sale implements Serializable {
    User user
    BigDecimal balance
    Client client
    BigDecimal moneyReceived
    String annotation
    Employee employee
    Boolean canceled = false

    Date dateCreated
    Date lastUpdated

    static constraints = {
        balance min: 1.0, scale: 2
        moneyReceived min: 1.0, scale: 2, validator: { moneyReceived, sale ->
            moneyReceived >= sale.balance
        }
        annotation nullable: true, maxSize: 255
        employee nullable: true
    }

    static namedQueries = {
        fromTo { from, to ->
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime()
        }

        byCurrentUser {
            eq "user", domainClass.application.mainContext.springSecurityService.currentUser
        }
    }

    List<SaleDetail> saleDetails
    static hasMany = [saleDetails: SaleDetail]

    String toString() { "by $toName for $user.fullName" }
}
