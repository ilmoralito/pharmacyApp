package ni.sb

class Provider {
	String name
  String address
  SortedSet products

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    address blank:false, unique:true
    products nullable:false
  }

  static hasMany = [products:String]

  static mapping = {
  	version false
  }

  String toString() { name }
}
