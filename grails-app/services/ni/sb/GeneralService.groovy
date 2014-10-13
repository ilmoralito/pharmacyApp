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

    def pro = MedicineOrder.get(6)
    pro.properties["bash"] = date2
    if (!pro.save()) {
      pro.errors.allErrors.each {
        print it
      }
    }
    


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
    }
    return results
  }

}//fin del servicio
