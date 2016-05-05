package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ConfigurationController {
    def distributorService

    static allowedMethods = [
        index: ["GET", "POST"]
    ]

    def index() {
        Configuration configuration = Configuration.get(1)

        if (request.post) {
            configuration.properties = params

            if (!configuration.save()) {
                configuration.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Verificar datos"
                return [configuration: configuration]
            }

            flash.message = "Datos actualizados"
        }

        [configuration: configuration]
    }
}
