package ni.sb

import groovy.transform.ToString

@ToString
class Payment {
    def creditSaleService

    Integer receiptNumber
    BigDecimal amount
    String reference
    String madeBy
    String madeByIdentityCard
    User attendedBy

    Date dateCreated
    Date lastUpdated

    static constraints = {
        receiptNumber unique: true, min: 1
        amount min: 1.0, scale: 2, validator: { amount, obj ->
            BigDecimal balanceToDate = obj.creditSaleService.getBalanceToDate(obj.creditSale)

            amount <= balanceToDate
        }
        reference blank: false
        madeBy blank: false
        madeByIdentityCard blank: false
    }

    static mapping = {
        sort dateCreated: "desc"
    }

    static belongsTo = [creditSale: CreditSale]
}
