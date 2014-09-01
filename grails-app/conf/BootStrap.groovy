import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
        //PROVIDERS
  			def provider1 = new Provider(
  				name:"provider1",
  				address:"address1",
          phone:"23114455",
  				products:["product1", "product2", "product3"]
  			)

  			if (!provider1.save()) {
          provider1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
          phone:"23114488",
  				products:["product4", "product5", "product6", "product7"]
  			)

  			if (!provider2.save()) {
          provider2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

  			assert Provider.count() == 2
  			assert provider1.products.size() == 3
  			assert provider2.products.size() == 4

        //CLIENTS
        def client1 = new Client(fullName:"juan perez", address:"Santa Pancha", identificationCard:"291-290160-0001w", phones:["23114455", "88554477"])

        if (!client1.save()) {
          client1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        def client2 = new Client(fullName:"fulano mengano", address:"De donde amarra la burra don mauro", identificationCard:"291-290160-0001x", phones:["23118855", "88559977"])

        if (!client2.save()) {
          client2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        assert Client.count() == 2

        //USERS
        def user = new User(
          username:"me@gmail.com",
          password:"123",
          email:"me@gmail.com",
          fullName:"Arnulfo Rolando Blandon"
        )

        if (!user.save()) {
          user.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        def adminRole = new Role(authority:"ROLE_ADMIN").save()
        new Role(authority:"ROLE_USER").save()

        assert Role.count() == 2

        UserRole.create user, adminRole, true

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 1
  		break
  	}
  }
  def destroy = {
  }
}
