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
        List<Map<String, String>> summary = data.groupBy { it.client }.collect { a ->
            [
                id: a.key.id,
                client: a.key.fullName,
                quantity: a.value.size()
            ]
        }.sort { -it.quantity }

        summary
    }

    def getBalanceSummary(Date from, Date to, Boolean canceled = false) {
        List<Sale> sales = Sale.createCriteria().list {
            eq "canceled", canceled
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }

        sales?.balance?.sum() ?: 0.0
    }
}
