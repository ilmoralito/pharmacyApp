package ni.sb

class Medicine extends Product {
	String code
  String genericName

  static constraints = {
    code blank:false
    genericName unique:true, nullable:true
    presentations nullable:false
  }

  static mapping = {
  	version false
  }

  static hasMany = [presentations:Presentation]

  String toString() { name }
}
