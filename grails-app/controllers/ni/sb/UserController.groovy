package ni.sb
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
class UserController {

	static allowedMethods = [
        list:["GET", "POST"],
        create:["GET", "POST"],
        edit:["GET", "POST"],
    	profile:"GET",
        updateProfile:"POST",
        password:"GET",
        updatePassword:"POST"
	]

	def springSecurityService
	def passwordEncoder

    def list(){
        if (request.method == "GET") {
            [userInstance:User.list()]
        }else{
            def userInstance = new User(
                username:params?.username,
                email:params?.username,
                password:"farmaciaSB",
                fullName:params?.fullName,
            )

            if (!userInstance.save(flush:true)) {
                render(view:"list", model:[user:userInstance, userInstance:User.list()])
                return false
            }

            def role = Role.findByAuthority(params?.authority)
            UserRole.create userInstance, role, true
            flash.message = "Usuario creado correctamente!!"
            redirect(action:"list")
        }
    }

    def edit(){
        def userInstance = User.get(params.id)
        if (!userInstance) { response.sendError 404 }

        if (request.method == "GET") {
            [userInstance:userInstance]
        }else{
            params.email = params.username

            userInstance.properties = params

            if (!userInstance.save()) {
                render(view:"edit", model:[userInstance:userInstance])
                return false
            }

            def r = UserRole.findByUser(userInstance)

            if (r.role.authority != params.authority) {
                def roleDelete = Role.findByAuthority(r.role.authority)
                UserRole.remove userInstance, roleDelete, true

                def role = Role.findByAuthority(params.authority)
                UserRole.create userInstance, role, true
            }

            redirect(action:"edit", params:[id:params.id])
            flash.message = "Datos de usuario actualiados correctamente!!"
        }
    }

    def enabled(){
        def userInstance = User.get(params.id)
        if (!userInstance) { response.sendError 404 }

        userInstance.properties = params

        redirect(action:"edit", params:[id:params.id])
        flash.message = "La cuanta fue actualizada correctamente!!"
    }

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
            flash.message = "La contraseña actual que ingreso no es correcta, intentelo nuevamente!!"
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
