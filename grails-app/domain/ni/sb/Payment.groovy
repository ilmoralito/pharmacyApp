package ni.sb

import groovy.transform.ToString

@ToString
class Payment {
    Long receiptNumber
    BigDecimal amount
    String reference
    String madeBy
    String madeByIdentityCard
    User attendedBy

    Date dateCreated
    Date lastUpdated

    static constraints = {
        receiptNumber unique: true, min: 1L
        amount min: 1.0, scale: 2
        reference blank: false
        madeBy blank: false
        madeByIdentityCard blank: false
    }

    static belongsTo = [creditSale: CreditSale]
}
