package ni.sb

import grails.transaction.Transactional

@Transactional
class DistributorService {
    Distributor getDistributor(Long id) {
        Distributor.get(id)
    }

    List<Distributor> getValidDistributors() {
        List<Distributor> dealers = Distributor.where {
            providers.size() > 0 && enabled == true
        }.list()
    }
}
