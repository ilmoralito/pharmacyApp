package ni.sb

class PurchaseOrder {
	Date dateCreated
  Date deadline //fecha tope
  String invoiceNumber //numero de factura
  BigDecimal balance //saldo <- calculate field
  String typeOfPurchase //tipo de compra

	Date lastUpdated

  static constraints = {
    deadline blank:false, validator:{ deadline ->
      def today = new Date()

      if (deadline <= today) {
        "notMatch"
      }
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
