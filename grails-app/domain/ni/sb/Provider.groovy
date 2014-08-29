package ni.sb

class Provider {
	String name
  String address
  String phone
  SortedSet products

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    address blank:false, unique:true
    phone blank:false, unique:true, validator: { phone ->
      if (phone.size() != 8 || !phone.isNumber()) {
        return ["notMatch"]
      }
    }
    products nullable:false
  }

  static hasMany = [products:String]

  static mapping = {
  	version false
  }

  String contactInformation() {
    "$address, $phone"
  }

  String toString() { name }
}
