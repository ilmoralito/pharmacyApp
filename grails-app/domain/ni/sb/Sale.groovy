package ni.sb

class Sale {
	

	Date dateCreated
	Date lastUpdated

  static constraints = {

  }

  static mapping = {
  	version false
  }

  static hasMany = []
  static belongsTo = []

  String toString() {  }
}
