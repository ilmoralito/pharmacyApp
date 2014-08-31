package ni.sb

class Provider implements Serializable {
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
        ["notMatch"]
      }
    }
    products nullable:false, minSize:1
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
