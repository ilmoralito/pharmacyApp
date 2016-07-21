package ni.sb

import grails.transaction.Transactional

@Transactional
class SaleService implements Serializable {
    def helperService
    def grailsApplication

    def getSalesFromField(String field) {
        def (Date from, Date to) = helperService.getDates(field)

        Sale.fromTo(from, to).list()
    }

    def getSummary(List<Sale> data) {
        data.groupBy { it.client }.collect { a ->
            [
                id: a.key.id,
                client: a.key.fullName,
                quantity: a.value.size()
            ]
        }.sort { -it.quantity }
    }

    List<CashSale> getCashSales(Date from, Date to, Boolean canceled = false) {
        CashSale.createCriteria().list {
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
            eq "canceled", canceled
        }
    }

    List<CreditSale> getCreditSales(Date from, Date to, Boolean canceled = false) {
        CreditSale.createCriteria().list {
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
            eq "canceled", canceled
        }
    }

    BigDecimal getBalanceSummary(List<Sale> sales) {
        sales?.balance?.sum() ?: 0.0
    }

    Long getNextInvoiceNumber() {
        Sale sale = Sale.list().last()
        def nextInvoiceNumber = sale ? sale[0].invoiceNumber + 1 : grailsApplication.config.ni.sb.invoiceNumber

        println Sale.list().last().invoiceNumber

        nextInvoiceNumber
    }
}
