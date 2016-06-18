package ni.sb

import org.grails.databinding.BindUsing
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
        amount min: 1.0, scale: 2
        reference blank: false
        madeBy blank: false
        madeByIdentityCard blank: false
    }

    static mapping = {
        sort dateCreated: "asc"
    }

    static belongsTo = [creditSale: CreditSale]
}

/*
, validator: { amount, obj ->
    BigDecimal balanceToDate = obj.creditSaleService.getBalanceToDate(obj.creditSale.payments, obj.creditSale.balance)

    amount <= balanceToDate
}
*/

/*
def beforeInsert() {
    if (creditSaleService.getTotalPayments(creditSale.payments) == creditSale.balance) {
        creditSale.canceled = true
    }
}
*/
