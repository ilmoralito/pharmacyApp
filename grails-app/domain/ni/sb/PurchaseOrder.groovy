package ni.sb

import org.grails.databinding.BindingFormat

class PurchaseOrder implements Serializable {
  @BindingFormat('yyyy-MM-dd')
	Date dateCreated

  @BindingFormat('yyyy-MM-dd')
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
