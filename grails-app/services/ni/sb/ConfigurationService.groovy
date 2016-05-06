package ni.sb

import grails.transaction.Transactional

@Transactional
class ConfigurationService {
    static transactional = false

    def loadConfiguration() {
        Configuration configuration = Configuration.get(1)

        configuration
    }
}
