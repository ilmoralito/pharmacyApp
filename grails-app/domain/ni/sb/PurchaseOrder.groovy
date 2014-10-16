package ni.sb

class PurchaseOrder implements Serializable {
  Provider provider
  Date dutyDate
  String invoiceNumber
  BigDecimal balance
  String typeOfPurchase
  Boolean status

  Date dateCreated
	Date lastUpdated

  static constraints = {
    provider nullable:false
    dutyDate nullable:false, validator: { dutyDate ->
      def today = new Date()

      if (dutyDate <= today) {
        return "purchaseOrder.dutyDate.notMatch"
      }
    }
    invoiceNumber blank:false, unique:true
    balance nullable:true
    typeOfPurchase inList:["Contado", "Credito"], maxSize:255
  }

  static mapping = {
  	version false
    sort dateCreated: "desc"
    items cascade: "all-delete-orphan"
  }

  List items
  static hasMany = [items:Item]

  def beforeInsert() {
    //TODO
    //status = (typeOfPurchase == "Contado") ? true : false
  }

  String toString() { invoiceNumber }
}
