import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def init = { servletContext ->
  	switch(Environment.current) {
  		case Environment.DEVELOPMENT:
        //PROVIDERS
        /*
  			def provider1 = new Provider(
  				name:"provider1",
  				address:"address1",
          phone:"23114455"
  			)

        def product1 = new Product(name:"product1", code:"123")

          def presentation1 = new Presentation(name:"Crema")

            def m1 = new Measure(name:"5g")
            def m2 = new Measure(name:"30g")

            presentation1.addToMeasures m1
            presentation1.addToMeasures m2

          def presentation2 = new Presentation(name:"Talco")

            def m3 = new Measure(name:"80mg")

            presentation2.addToMeasures m3

          product1.addToPresentations presentation1
          product1.addToPresentations presentation2

        def product2 = new Product(name:"product2", code:"456")

          def presentation3 = new Presentation(name:"Soluble")

            def m4 = new Measure(name:"25g")

            presentation3.addToMeasures m4

          product2.addToPresentations presentation3

        def product3 = new Product(name:"product3", code:"789")

          def presentation4 = new Presentation(name:"Spray")

            def m5 = new Measure(name:"120ml")
            def m6 = new Measure(name:"250ml")

            presentation4.addToMeasures m5
            presentation4.addToMeasures m6

          def presentation5 = new Presentation(name:"Tableta")

            def m7 = new Measure(name:"4tabs")

            presentation5.addToMeasures m7

          product3.addToPresentations presentation4
          product3.addToPresentations presentation5

        def providerOneProducts = [product1, product2, product3]

        providerOneProducts.each { product ->
          provider1.addToProducts product
        }

  			if (!provider1.save()) {
          provider1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }
        */

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
          phone:"23114488"
  			)

        def product4 = new Product(name:"product4", code:"10111")

          def presentation6 = new Presentation(name:"Spray")

            presentation6.addToMeasures(new Measure(name:"250ml")).addToMeasures(new Measure(name:"356ml"))

          product4.addToPresentations presentation6

        def product5 = new Product(name:"product5", code:"45614")

          def presentation7 = new Presentation(name:"Spray")

            presentation7.addToMeasures(new Measure(name:"475ml"))

          product5.addToPresentations presentation7

        def providerTwoProducts = [product4, product5]

        providerTwoProducts.each { product ->
          provider2.addToProducts product
        }

  			if (!provider2.save()) {
          provider2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

  			assert Provider.count() == 1
  			//assert provider1.products.size() == 3
  			assert provider2.products.size() == 2
        //println provider2.products.presentations.measures

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
