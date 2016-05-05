package ni.sb

import grails.transaction.Transactional

@Transactional
class DistributorService {
    Distributor getDistributor(Long id) {
        Distributor.get(id)
    }

    List<Distributor> getValidDistributors() {
        List<Distributor> dealers = Distributor.where {
            enabled == true
        }.list()
    }

    Integer getMaximumDistributorDaysToPay(List<Distributor> distributors) {
        distributors.daysToPay.max()
    }
}
