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
          phone:"23114455"
  			)

        def product1 = new Product(name:"product1", code:"123")
        def product2 = new Product(name:"product2", code:"456")
        def product3 = new Product(name:"product3", code:"789")

        def providerOneProducts = [product1, product2, product3]

        providerOneProducts.each { product ->
          provider1.addToProducts product
        }

  			if (!provider1.save()) {
          provider1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
          phone:"23114488"
  			)

        def product4 = new Product(name:"product4", code:"10111")
        def product5 = new Product(name:"product5", code:"45614")
        def product6 = new Product(name:"product6", code:"78911")
        def product7 = new Product(name:"product7", code:"78910")

        def providerTwoProducts = [product4, product5, product6, product7]

        providerTwoProducts.each { product ->
          provider2.addToProducts product
        }

  			if (!provider2.save()) {
          provider2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

  			assert Provider.count() == 2
  			assert provider1.products.size() == 3
  			assert provider2.products.size() == 4

        //CLIENTS
        def client1 = new Client(fullName:"juan perez", address:"Address1", identificationCard:"291-290160-0001w", phones:["23114455", "88554477"])

        if (!client1.save()) {
          client1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        def client2 = new Client(fullName:"fulano mengano", address:"Address2", identificationCard:"291-290160-0001x", phones:["23118855", "88559977"])

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
