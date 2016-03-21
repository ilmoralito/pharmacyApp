package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
class CheckPasswordController {
    def springSecurityService
    def passwordEncoder

    static allowedMethods = [
        index: "GET",
        validate: "POST"
    ]

    def index() { }

    def validate(ValidateCommand command) {
        if (command.hasErrors()) {
            command.errors.allErrors.each { error ->
                log.error "[$error.field: $error.defaultMessage]"
            }

            flash.message = "Dato incorrectos"

            redirect action: "index"
            return false
        }

        User user = springSecurityService.currentUser

        if (!user) {
            response.sendError 404
        }

        user.properties["password"] = command.newPassword
        user.save(flush: true)

        flash.message = "Actualizacion aplicada"
        redirect controller: "login"
    }
}

class ValidateCommand {
    String newPassword
    String repeatNewPassword

    static constraints = {
        newPassword blank: false, validator: { newPassword ->
            if (newPassword == "temporal") {
                return "not.valid.password"
            }
        }
        repeatNewPassword blank: false, validator: { repeatNewPassword, obj ->
            if (repeatNewPassword != obj.newPassword) {
                return "not.equal.password"
            }
        }
    }
}
