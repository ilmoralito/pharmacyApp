package ni.sb

class Pay {
  User user
  Integer receiptNumber
  BigDecimal payment
  BigDecimal change

	Date dateCreated
	Date lastUpdated

  static constraints = {
    user nullable:false
    receiptNumber nullable:false, unique:true
    payment nullable:false, scale:2
    change nullable:false, scale:2
  }

  static mapping = {
  	version false
  }

  static belongsTo = [saleToClient:SaleToClient]

  String toString() { payment }
}
