package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController {
	static defaultAction = ""

	static allowedMethods = [
    	profile:"GET",
        updateProfile:"POST",
        password:"GET",
        updatePassword:"POST"
	]

	def springSecurityService
	def passwordEncoder

  	@Secured(['ROLE_ADMIN','ROLE_USER'])
    def profile(){
        def userInstance = springSecurityService.currentUser
        [userInstance:userInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def updateProfile(){
        def userInstance = User.get(params.id)

        userInstance.properties = params

        if (!userInstance.save()) {
            render(view:'profile', model:[userInstance:userInstance])
            return false
        }else {
            springSecurityService.reauthenticate userInstance.username
            flash.message = "Datos Actualizados!!"
            redirect(action:"profile")
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def password(){
        def userInstance = springSecurityService.currentUser
        [userInstance:userInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def updatePassword(passwordChangeCommand cmd){
        def userInstance = springSecurityService.currentUser

        if (cmd.hasErrors()) {
        	flash.message = "Las contraseñas no coinciden, intentelo nuevamente!!"
            redirect (action:"password")
            return false
        }

        String currentPassword = cmd.currentPassword
        String password = cmd.password
        String confirmPassword = cmd.confirmPassword

        if (passwordEncoder.isPasswordValid(userInstance.password, currentPassword, null)) {
            userInstance.properties["password"] = params
            if (userInstance.save(flush:true)) {
                flash.message = "Su contraseña fue cambiada exitosamente"
                redirect (action:"password")
                return false
            }
        } else {
            flash.message = "La contraseña actula no es correcta, ingrese su contraseña actual nuevamente!!"
            redirect action:"password"
            return false
        }

    }

}

class passwordChangeCommand {
    def springSecurityService
    String currentPassword
    String password
    String confirmPassword

    static constraints = {
        password validator:{ val, obj ->
        val == obj.confirmPassword
        }
    }
}
