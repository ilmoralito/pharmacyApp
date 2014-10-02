package ni.sb

class Medicine extends Product {
	String code

  static constraints = {
    code blank:false
    presentations nullable:false
  }

  static mapping = {
  	version false
  }

  static hasMany = [presentations:Presentation]

  String toString() { name }
}
