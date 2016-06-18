package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {
    def springSecurityService

    static defaultAction = "list"
    static allowedMethods = [
        list: "GET",
        create: "POST",
        show: "GET",
        update: "POST",
        profile: ["GET", "POST"],
        password: ["GET", "POST"]
    ]

    def list() {
        Boolean enabled = params.boolean("enabled") == null ? true : params.boolean("enabled")
        List<User> users = User.where {
            enabled == enabled
        }.list(params)

        [users: users]
    }

    def create() {
        User user = new User(params)

        if (!user.save()) {
            user.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = user
        } else {
            Role role = Role.findByAuthority(params?.authority)
            UserRole.create user, role, true
        }

        flash.message = user.hasErrors() ? "A ocurrido un error" : "Accion concluida"
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
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = user
                flash.message = user.hasErrors() ? "A ocurrido un error" : "Accion concluida"
            }
        }

        [user: user]
    }


    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def password(UpdatePasswordCommand command){
        if (request.method == "POST") {

            if (command.hasErrors()) {
                command.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = command
                flash.message = "Datos incorrectos"
                return
            }

            User currentUser = springSecurityService.currentUser
            currentUser.password = command.newPassword

            if (!currentUser.save()) {
                return
            }

            flash.message = currentUser.hasErrors() ? "A ocurrido un error" : "Accion concluida"
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
