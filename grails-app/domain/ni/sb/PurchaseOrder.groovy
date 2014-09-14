package ni.sb

class PurchaseOrder implements Serializable {
  Date dutyDate
  String invoiceNumber
  BigDecimal balance
  String typeOfPurchase
  Boolean status = true

  Date dateCreated
	Date lastUpdated

  static constraints = {
    dutyDate nullable:false, validator: { dutyDate ->
      def today = new Date()

      if (dutyDate <= today) {
        return "purchaseOrder.dutyDate.notMatch"
      }

      true
    }
    invoiceNumber blank:false, unique:true
    balance nullable:true
    typeOfPurchase inList:["Contado", "Credito"], maxSize:255
  }

  static mapping = {
  	version false
  }

  List items
  static hasMany = [items:Item]

  String toString() { invoiceNumber }
}
