package ni.sb

class Sale implements Serializable {
  def springSecurityService

  User user
  BigDecimal balance = 0

	Date dateCreated
	Date lastUpdated

  static constraints = {
    user nullable:false
    balance scale:2
  }

  def beforeInsert() {
    user = springSecurityService.currentUser
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
