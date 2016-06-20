package ni.sb

import grails.transaction.Transactional

@Transactional
class CreditSaleService {
    def getCompanyBalance(List credits, List<Boolean> paidOut) {
        BigDecimal companyBalance = 0.0

        credits.each { a ->
            a.each { b ->
                if (b.paidOut in paidOut) {
                    if (b.balance != b.balanceToDate) {
                        companyBalance +=  (b.balance - b.balanceToDate)
                    } else {
                        companyBalance += b.balance
                    }
                }
            }
        }

        companyBalance
    }

    BigDecimal getTotalPayments(List<Payment> payments) {
        payments.amount.sum() ?: 0
    }

    BigDecimal getBalanceToDate(List<Payment> payments, BigDecimal balance) {
        BigDecimal totalPayments = getTotalPayments(payments)
        BigDecimal balanceToDate = balance - totalPayments

        balanceToDate
    }
}
