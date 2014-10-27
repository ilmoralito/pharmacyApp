package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
  def springSecurityService

	static defaultAction = "list"
	static allowedMethods = [
		list:"GET",
    getItemsByProduct:"GET",
    filterMedicinesByGenericName:"GET"
	]

  def list() {
  	def today = new Date()

  	[sales:Sale.salesFromTo(today, today + 1).list()]
  }

  def getItemsByProduct(Product product) {
    def query = Item.where {
      product == product
    }

    def items = query.list()

    items
  }

  def createSaleToClientFlow = {
    init {
      action {
        def clients = Client.where {
          status == true
        }

        [clients:clients.list()]
      }

      on("success").to "selectCustomer"
    }

    selectCustomer {
      on("confirm") { SelectCustomer command ->
        if (command.hasErrors()) {
          command.errors.allErrors.each { error ->
            log.error "[$error.field: $error.defaultMessage]"
          }

          return error()
        }

        [client:command.client, typeOfPurchase:command.typeOfPurchase]
      }.to "managePurchase"

      on("cancel").to "done"
    }

    managePurchase {
      action {
        def criteria = MedicineOrder.createCriteria()
        def medicines = criteria.list {
          projections {
            groupProperty "product"
          }
        }

        def genericNames = medicines.groupBy { it.genericName }.keySet() as List

        [medicines:medicines, genericNames:genericNames]
      }

      on("success").to "medicine"
    }

    medicine {
      on("filter") {
        def criteria = MedicineOrder.createCriteria()
        def results = criteria.list {
          product {
            eq "name", params?.product
          }
        }

        def medicinesGrouped = results.groupBy { it.presentation }

        def medicinesFiltredByGenericName = params?.genericName ? flow.medicines.findAll { it.genericName == params?.genericName } : null

        [results:medicinesGrouped, product:params?.product, genericName:params?.genericName, medicinesFiltredByGenericName:medicinesFiltredByGenericName]
      }.to "medicine"

      on("selectCustomer").to "selectCustomer"
      on("medicine").to "managePurchase"
    }

    done() {
      redirect controller:"sale", action:"list"
    }
  }

  def filterMedicinesByGenericName(String genericName) {
    def criteria = MedicineOrder.createCriteria()
    def results = criteria.list {
      product {
        eq "genericName", genericName
      }

      projections {
        groupProperty "product"
      }
    }

    render(contentType:"application/json") {
      results
    }
  }
}

class SelectCustomer implements Serializable {
  Client client
  String typeOfPurchase

  static constraints = {
    importFrom SaleToClient
  }
}

class SaleDetailCommand implements Serializable {
  Item item
  Integer quantity
  BigDecimal total = 1

  static constraints = {
    importFrom SaleDetail
  }
}
