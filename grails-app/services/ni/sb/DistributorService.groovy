package ni.sb

import grails.transaction.Transactional

@Transactional
class DistributorService {

    List<Distributor> getValidDistributors() {
        List<Distributor> dealers = Distributor.where {
            providers.size() > 0 && enabled == true
        }.list()
    }
}
