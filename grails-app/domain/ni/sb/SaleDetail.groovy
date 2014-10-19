package ni.sb

class SaleDetail implements Serializable {
	Item item
  Presentation presentation
  String measure
  Integer quantity
  BigDecimal total

	Date dateCreated
	Date lastUpdated

  static constraints = {
    item nullable:false
    presentation nullable:false
    measure blank:false
    quantity min:1, nullable:false
    total scale:2, min:0.1
  }

  static mapping = {
  	version false
  }

  static belongsTo = [sale:Sale]

  String toString() { item }
}
