package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService

    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        profile: ["GET", "POST"],
        password: ["GET", "POST"],
    ]

    def list(){
        if (request.method == "POST") {
            
        }

        [users: User.list()]
    }

    def show(Long id) {
        
    }

    def update(Long id) {
        
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def profile(){
        User user = springSecurityService.currentUser

        if (request.method == "POST") {
            user.properties["username", "email", "fullName"] = params

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"
            }
        }

        [user: user]
    }


    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def password(UpdatePasswordCommand command){
        if (request.method == "POST") {

            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"
                return
            }

            User currentUser = springSecurityService.currentUser
            currentUser.properties["password"] = command.newPassword

            if (!currentUser.save()) {
                return
            }

            flash.message = "Clave actualizada"
        }
    }
}

class UpdatePasswordCommand {
    def springSecurityService
    def passwordEncoder

    String currentPassword
    String newPassword
    String repeatNewPassword

    static constraints = {
        currentPassword blank: false, validator: { currentPassword, obj ->
            String currentUserPassword = obj.springSecurityService.currentUser.password

            obj.passwordEncoder.isPasswordValid(currentUserPassword, currentPassword, null)
        }
        newPassword blank:false
        repeatNewPassword blank: false, validator: { repeatNewPassword, obj ->
            repeatNewPassword == obj.newPassword
        }
    }
}
