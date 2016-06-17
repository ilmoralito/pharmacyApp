package ni.sb

import grails.transaction.Transactional

@Transactional
class CreditSaleService {

    BigDecimal getBalanceToDate(CreditSale creditSale) {
        BigDecimal totalPayments = creditSale?.payments?.amount?.sum() ?: 0
        BigDecimal balanceToDate = creditSale.balance - totalPayments

        balanceToDate
    }
}
