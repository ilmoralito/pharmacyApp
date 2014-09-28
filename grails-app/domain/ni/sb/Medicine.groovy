package ni.sb

class Medicine extends Product {
	String code

  static constraints = {
    code blank:false
  }

  static mapping = {
  	version false
  }

  static hasMany = [presentations:Presentation]

  String toString() { code }
}
