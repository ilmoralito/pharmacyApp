package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService

    static allowedMethods = [
        list: ["GET", "POST"],
        changeEnabledState: "GET",
        show: "GET",
        update: "POST",
        profile: ["GET", "POST"],
        password: ["GET", "POST"]
    ]

    def list() {
        if (request.method == "POST") {
            User user = new User(params)

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error. Intentalo otravez"
                return
            } else {
                Role role = Role.findByAuthority(params?.authority)
                UserRole.create user, role, true
                
            }
        }

        [users: User.list()]
    }

    def changeEnabledState(Long id) {
        User user = User.get(id)

        if (!user) {
            response.sendError 404
        }

        user.properties["enabled"] = !user.enabled
        user.save()

        redirect action: "list"
    }

    def show(Long id) {
        User user = User.get(id)

        if (!user) {
            response.sendError 404
        }

        [user: user]
    }

    def update(Long id) {
        User user = User.get(id)

        if (!user) {
            response.sendError 404
        }

        user.properties["email", "fullName"] = params

        UserRole.removeAll user, true

        Role role = Role.findByAuthority(params?.authority)
        UserRole.create user, role, true

        redirect action: "show", id: id
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
