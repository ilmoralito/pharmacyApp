package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        profile: ["GET", "POST"],
        password: ["GET", "POST"]
    ]

    def list(Boolean enabled, Boolean filtered) {
        Closure users = {
            if (enabled == null) {
                enabled = true
            }

            User.findAllByEnabled(enabled)
        }

        if (request.post) {
            User user = new User(params)

            if (!user.save()) {
                user.errors.allErrors.each { error ->
                    log.error "[$error.field: $error.defaultMessage]"
                }

                flash.message = "A ocurrido un error"

                return [users: users()]

                // TODO: FIX error 
                // object references an unsaved transient instance - save the transient instance before flushing: ni.sb.User
                //return [users: users(), user: user]
            } else {
                Role role = Role.findByAuthority(params?.authority)
                UserRole.create user, role, true
            }
        }

        [users: users()]
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

        user.properties = params

        UserRole.removeAll user, true

        Role role = Role.findByAuthority(params?.authority)
        UserRole.create user, role, true

        redirect action: "show", id: id
    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def profile(){
        User user = springSecurityService.currentUser

        if (request.method == "POST") {
            user.properties["fullName", "email", "telephoneNumber"] = params

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
