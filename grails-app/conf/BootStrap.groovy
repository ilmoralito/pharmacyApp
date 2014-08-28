import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
  			def provider1 = new Provider(
  				name:"provider1",
  				address:"address1",
  				products:["product1", "product2", "product3"]
  			)

  			provider1.save()

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
  				products:["product4", "product5", "product6", "product7"]
  			)

  			provider2.save()

  			assert Provider.count() == 2
  			assert provider1.products.size() == 3
  			assert provider2.products.size() == 4

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
