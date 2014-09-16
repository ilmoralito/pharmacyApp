import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def itemService

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

          def presentation1 = new Presentation(name:"Crema", measures:["15g", "30g"])
          def presentation2 = new Presentation(name:"Talco", measures:["80gm"])

          product1.addToPresentations presentation1
          product1.addToPresentations presentation2

        def product2 = new Product(name:"product2", code:"456")

          def presentation3 = new Presentation(name:"Soluble", measures:["25g", "100g", "125g"])
          product2.addToPresentations presentation3

        def product3 = new Product(name:"product3", code:"789")

          def presentation4 = new Presentation(name:"Spray", measures:["150ml", "356ml", "475ml", "159gm"])
          def presentation5 = new Presentation(name:"Tableta", measures:["2tabs", "300tabs"])

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

  			def provider2 = new Provider(
  				name:"provider2",
  				address:"address2",
          phone:"23114488"
  			)

        def product4 = new Product(name:"product4", code:"10111")

          def presentation6 = new Presentation(name:"Spray", measures:["250ml", "356ml"])
          product4.addToPresentations presentation6

        def product5 = new Product(name:"product5", code:"45614")

          def presentation7 = new Presentation(name:"Spray", measures:["475ml"])
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

  			assert Provider.count() == 2
  			assert provider1.products.size() == 3
  			assert provider2.products.size() == 2

        //PURCHASE ORDER
        def purchaseOrder = new PurchaseOrder(
          dutyDate:new Date() + 31,
          invoiceNumber:"001001",
          typeOfPurchase:"Credito",
          balance:0
        )

        def item1 = new Item(
          product:product1,
          presentation:presentation1,
          measure:presentation1.measures[0],//15g
          quantity:100,
          purchasePrice:15,
          sellingPrice:20,
          bash:new Date() + 90,
          total:100 * 15,
          purchaseOrder:purchaseOrder
        )


        def item2 = new Item(
          product:product1,
          presentation:presentation1,
          measure:presentation1.measures[1],//30g
          quantity:100,
          purchasePrice:25,
          sellingPrice:45,
          bash:new Date() + 100,
          total:100 * 25,
          purchaseOrder:purchaseOrder
        )

        purchaseOrder.addToItems item1
        purchaseOrder.addToItems item2

        purchaseOrder.balance += item1.total
        purchaseOrder.balance += item2.total

        if (!purchaseOrder.save()) {
          purchaseOrder.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        assert purchaseOrder.count() == 1
        assert Item.count() == 2
        assert purchaseOrder.balance == 4000

        /*
        purchaseOrder.removeFromItems item2
        purchaseOrder.save()
        item2.delete(flush:true)

        assert purchaseOrder.balance == 1500
        //*/

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
