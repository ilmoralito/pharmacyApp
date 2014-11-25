package ni.sb

import grails.transaction.Transactional
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Transactional
class GeneralService {

  def quantity() {
  	def c = Item.createCriteria()
    def results = c.list {
        le("quantity", 10)
    }
    return results
  }

  def expire(){
  	def today = new Date()
  	def proyectionDate = new Date() + 90

   	def c = MedicineOrder.createCriteria()
    def results = c.list {
        between("bash",today, proyectionDate)
        order("bash", "desc")

        projections {
            property "bash", "bash"
            property "product", "product"
            property "quantity", "quantity"
        }
        resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
    }

    results.each{ r ->
        r.remainingDays = r.bash - today
    }

    return results
  }

  def expired(){
  	def today = new Date()
  	def date = new Date() - 1
    def date2 = new Date() - 4

  	def c = MedicineOrder.createCriteria()
    def results = c.list {
        le("bash",date)
        order("bash", "desc")

        projections {
            property "bash", "bash"
            property "product", "product"
            property "quantity", "quantity"
        }
        resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
    }

    results.each{ r ->
        r.daysPastDue = today - r.bash
    }

    return results
  }

  def pendingOrders(){
  	def today = new Date()
  	def date = new Date() + 7

  	def c = PurchaseOrder.createCriteria()
    def results = c.list {
        le("dutyDate",date)
        order("dutyDate", "desc")

        projections {
            property "dutyDate", "dutyDate"
            property "provider", "provider"
            property "balance", "balance"
            property "invoiceNumber", "invoiceNumber"
        }
         resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
    }

    results.each{ r ->
        r.remainingDays = r.dutyDate - today
    }

    return results
  }

  def clientPayments(){
    def today = new Date() + 7

    def c = SaleToClient.createCriteria()
    def results = c.list {
        eq("status","Pendiente")
        order("dateCreated", "desc")

        projections {
            property "dateCreated", "dateCreated"
            property "balance", "balance"
            property "client", "client"
            property "id", "id"
        }
        resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
    }

    results.each{ r ->
       r.proyectionDate = r.dateCreated + 30
    }

    def payment = results.findAll{it.proyectionDate <= today}

    payment.each{ p ->
      def sale = SaleToClient.get(p.id)

      def criteria = Pay.createCriteria()
      def totalPayment = criteria.get{
          eq("saleToClient", sale)
            projections {
              sum ('payment')
            }
      }

      if (!totalPayment) {
        totalPayment = 0
      }

      p.totalPayment = totalPayment
      p.currentBalance = p.balance - totalPayment
    }

    return payment
  }

}//fin del servicio
