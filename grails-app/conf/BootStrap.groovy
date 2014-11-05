import ni.sb.*
import grails.util.Environment
import grails.util.Holders

class BootStrap {
  def itemService

  def init = { servletContext ->

    switch(Environment.current) {
      case Environment.DEVELOPMENT:
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //PROVIDERS
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        def provider1 = new Provider(name:"provider1", address:"address1", phone:"23114455")

        def product1 = new Product(name:"product1")
        def product2 = new Product(name:"product2")
        def product3 = new Product(name:"product3")
        def medicine1 = new Medicine(name:"Medicine1", code:"1234", genericName:"someGenericName")
          
          def presentation1 = new Presentation(name:"Crema", measures:["5g", "15g"])
          medicine1.addToPresentations(presentation1)

        def medicine2 = new Medicine(name:"Medicine2", code:"1235", genericName:"someGenericName")
          
          def presentation2 = new Presentation(name:"Suspencion", measures:["360ml"])
          medicine2.addToPresentations(presentation2)

        def medicine3 = new Medicine(name:"Medicine3", code:"1236", genericName:"anotherGenericName")
          
          def presentation3 = new Presentation(name:"Inyectable", measures:["20ml", "25ml"])
          medicine3.addToPresentations(presentation3)

        def brandProduct0 = new BrandProduct(name:"Papel higienico")
          def brand0 = new Brand(name:"Colgate", details:["Pequeno", "Grande"])

          brandProduct0.addToBrands(brand0)

        def productsInProvider1 = [product1, product2, product3, medicine1, medicine2, medicine3, brandProduct0]

        productsInProvider1.each { product ->
          provider1.addToProducts product
        }

        if (!provider1.save()) {
          provider1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        def provider2 = new Provider(name:"provider2", address:"address2", phone:"23114488")

        def product4 = new Product(name:"product4")
        def product5 = new Product(name:"product5")
        def brandProduct1 = new BrandProduct(name:"Pasta dental")
          def brand1 = new Brand(name:"Colgate", details:["Clasico", "Extreme"])
          def brand2 = new Brand(name:"Zoro", details:["Riorusen"])

          brandProduct1.addToBrands(brand1).addToBrands(brand2)

        def brandProduct2 = new BrandProduct(name:"Jabon")
          def brand3 = new Brand(name:"Palmolive", details:["Clasico", "Moderno", "Contemporanio"])
          def brand4 = new Brand(name:"Dermacare", details:["1% Clotrimazol", "5% Water 7"])
          def brand5 = new Brand(name:"Colgate", details:["Detalle1", "Detalle2"])

          brandProduct2.addToBrands(brand3).addToBrands(brand4).addToBrands(brand5)

        def productsInProvider2 = [product4, product5, brandProduct1, brandProduct2]

        productsInProvider2.each { product ->
          provider2.addToProducts product
        }

        if (!provider2.save()) {
          provider2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        assert Provider.count() == 2
        assert provider1.products.size() == 7
        assert provider2.products.size() == 4
        assert BrandProduct.count() == 3
        assert Medicine.count() == 3
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //PROVIDERS
        //+++++++++++++++++++++++++++++++++++++++++++++++++++

        //|||||||||||||||||||||||||||||||||||||||||||||||||||

        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //PURCHASE ORDER
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        def today = new Date()
        def purchaseOrder1 = new PurchaseOrder(provider:provider1, dutyDate:today + 31, invoiceNumber:"001", typeOfPurchase:"Contado", balance:0, status:true)

        def item1 = new Item(product:product1, quantity:100, purchasePrice:15, sellingPrice:15 + (15 * 0.25), total:100 * 15)
        def item2 = new Item(product:product2, quantity:100, purchasePrice:25, sellingPrice:25 + (25 * 0.25), total:100 * 25)
        def item3 = new Item(product:product3, quantity:50, purchasePrice:10, sellingPrice:55 + (55 * 0.25), total:50 * 10)

        def m0 = new MedicineOrder(
          product:medicine1,
          presentation:presentation1,
          measure:presentation1.measures[1],
          bash:today + 90,
          quantity:50,
          purchasePrice:16,
          sellingPrice:16 + (16 * 0.25),
          total:50 * 16
        )

        def brandProductOrder1 = new BrandProductOrder(product:brandProduct0, quantity:25, purchasePrice:15, sellingPrice:15 + (15 * 0.25), total:25 * 15, brand:brand0, detail:"Grande")

        purchaseOrder1.addToItems(item1).addToItems(item2).addToItems(item3).addToItems(m0).addToItems(brandProductOrder1)

        purchaseOrder1.balance += [item1, item2, item3, m0, brandProductOrder1].sum { it.total }

        if (!purchaseOrder1.save()) {
          purchaseOrder1.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        def purchaseOrder2 = new PurchaseOrder(provider:provider2 ,dutyDate:today + 60, invoiceNumber:"002", typeOfPurchase:"Contado", balance:0, status:true)

        def item4 = new Item(product:product4, quantity:150, purchasePrice:9.50, sellingPrice:9.50 + (9.50 * 0.25), total:150 * 9.50)
        def item5 = new Item(product:product5, quantity:50, purchasePrice:16, sellingPrice:16 + (16 * 0.25), total:50 * 16)

        def m1 = new MedicineOrder(
          product:medicine1,
          presentation:presentation1,
          measure:presentation1.measures[1],
          bash:today + 160,
          quantity:50,
          purchasePrice:16,
          sellingPrice:16 + (16 * 0.25),
          total:50 * 16
        )

        def m2 = new MedicineOrder(
          product:medicine2,
          presentation:presentation2,
          measure:presentation2.measures[0],
          bash:today + 88,
          quantity:50,
          purchasePrice:10,
          sellingPrice:10 + (10 * 0.25),
          total:50 * 10
        )

        def m3 = new MedicineOrder(
          product:medicine3,
          presentation:presentation3,
          measure:presentation3.measures[1],
          bash:today + 99,
          quantity:50,
          purchasePrice:5,
          sellingPrice:5 + (5 * 0.25),
          total:50 * 5
        )

        purchaseOrder2.addToItems(item4).addToItems(item5).addToItems(m1).addToItems(m2).addToItems(m3)

        purchaseOrder2.balance += [item4, item5, m1, m2, m3].sum { it.total }

        if (!purchaseOrder2.save()) {
          purchaseOrder2.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }
        }

        assert PurchaseOrder.count() == 2
        assert Item.count() == 10
        assert purchaseOrder1.balance == 5675
        assert purchaseOrder2.balance == 3775.00

        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //PURCHASE ORDER
        //+++++++++++++++++++++++++++++++++++++++++++++++++++


        //|||||||||||||||||||||||||||||||||||||||||||||||||||

        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //CLIENT
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
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
        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //CLIENT
        //+++++++++++++++++++++++++++++++++++++++++++++++++++

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

        //|||||||||||||||||||||||||||||||||||||||||||||||||||

        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        //SALES
        //+++++++++++++++++++++++++++++++++++++++++++++++++++

        def sale1 = new SaleToClient(user:user, balance:1, client:client1, typeOfPurchase:"Credito", status:"Pendiente")
        def saleDetailItem1 = new SaleDetail(item:item1, quantity:5, total:item1.sellingPrice * 5)
        item1.quantity -= saleDetailItem1.quantity

        sale1.addToSaleDetails(saleDetailItem1)

        sale1.balance = saleDetailItem1.total

        if (!sale1.save()) {
          sale1.errors.allErrors.each { error -> log.error "[$error.field:$error.defaultMessage]" }
        }

        def sale2 = new SaleToClient(user:user, balance:1, client:client2, typeOfPurchase:"Contado", status:"Cancelado")
        def saleDetailItem2 = new SaleDetail(item:item1, quantity:5, total:item1.sellingPrice * 5)
        def saleDetailItem3 = new SaleDetail(item:item2, quantity:5, total:item1.sellingPrice * 5)
        item1.quantity -= saleDetailItem1.quantity
        item2.quantity -= saleDetailItem2.quantity

        sale2.addToSaleDetails(saleDetailItem2).addToSaleDetails(saleDetailItem3)

        sale2.balance = saleDetailItem2.total + saleDetailItem3.total

        if (!sale2.save()) {
          sale2.errors.allErrors.each { error -> log.error "[$error.field:$error.defaultMessage]" }
        }

        assert sale1.balance == 93.75
        assert sale2.balance == 187.50
      break
    }
  }
  def destroy = {
  }
}
