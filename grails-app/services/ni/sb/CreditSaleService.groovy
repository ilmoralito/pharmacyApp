package ni.sb

import grails.transaction.Transactional

@Transactional
class CreditSaleService {

    BigDecimal getTotalPayments(List<Payment> payments) {
        payments.amount.sum() ?: 0
    }

    BigDecimal getBalanceToDate(List<Payment> payments, BigDecimal balance) {
        BigDecimal totalPayments = getTotalPayments(payments)
        BigDecimal balanceToDate = balance - totalPayments

        balanceToDate
    }
}
