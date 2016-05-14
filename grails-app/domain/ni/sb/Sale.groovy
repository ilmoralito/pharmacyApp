package ni.sb

import groovy.transform.ToString

@ToString
class Sale implements Serializable {
    User user
    BigDecimal balance
    Boolean canceled = false
    String reazonOfCanelation
    Date dateOfCancelation
    String annotation

    Date dateCreated
    Date lastUpdated

    static constraints = {
        balance min: 1.0, scale: 2
        reazonOfCanelation nullable: true, maxSize: 255, validator: { reazonOfCanelation, obj ->
            if (obj.canceled) {
                return reazonOfCanelation != ""
            }
        }
        dateOfCancelation nullable: true
        annotation nullable: true, maxSize: 255
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
}
