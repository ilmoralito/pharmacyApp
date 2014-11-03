package ni.sb

class Sale implements Serializable {
  User user
  BigDecimal balance = 0
  Boolean canceled = false

	Date dateCreated
	Date lastUpdated

  static constraints = {
    user nullable:false
    balance scale:2
  }

  static mapping = {
  	version false
    sort dateCreated: "desc"
  }

  static namedQueries = {
    salesFromTo { from, to ->
      ge "dateCreated", from.clearTime()
      le "dateCreated", to.clearTime()
    }
  }

  static hasMany = [saleDetails:SaleDetail]

  String toString() { dateCreated }
}
