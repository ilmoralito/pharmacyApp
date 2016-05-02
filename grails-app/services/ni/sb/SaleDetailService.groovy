package ni.sb

import grails.transaction.Transactional

@Transactional
class SaleDetailService {

    List<SaleDetail> getSaleDetails(Date from, Date to, Boolean canceled = false) {
        SaleDetail.createCriteria().list {
            sale {
                eq "canceled", canceled
            }

            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }
    }

    List getSaleDetailSummary(List<SaleDetail> salesDetails) {
        if (salesDetails) {
            salesDetails.groupBy { it.item }.collect { a ->
                [
                    item: a.key,
                    quantity: a.value.quantity.sum()
                ]
            }.sort { -it.quantity }
        } else {
            []
        }
    }
}
