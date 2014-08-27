package ni.sb

class Provider {
	String name
  String address

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    address blank:false, unique:true
  }

  static mapping = {
  	version false
  }

  String toString() { name }
}
