package ni.sb

import grails.transaction.Transactional


@Transactional
class SaleService {
    def helperService

    def getSalesFromField(String field) {
        def (Date from, Date to) = helperService.getDates(field)

        log.info "Quering from ${from.format('yyyy-MM-dd')} to ${to.format('yyyy-MM-dd')}"
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

    def getBalanceSummary(List<Sale> sales) {
        sales?.balance?.sum() ?: 0.0
    }

    def getSales(Date from, Date to, Boolean canceled = false) {
        Sale.createCriteria().list {
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
            eq "canceled", canceled
        }
    }
}
