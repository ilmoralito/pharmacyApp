package ni.sb

class Sale implements Serializable {
  BigDecimal balance = 0

	Date dateCreated
	Date lastUpdated

  static constraints = {
    balance scale:2
  }

  static mapping = {
  	version false
    sort dateCreated: "desc"
  }

  static hasMany = [saleDetails:SaleDetail]

  String toString() { dateCreated }
}
