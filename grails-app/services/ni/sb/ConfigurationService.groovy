package ni.sb

import grails.transaction.Transactional

@Transactional
class ConfigurationService {
    static transactional = false

    def loadConfiguration() {
        Configuration.get(1)
    }
}
