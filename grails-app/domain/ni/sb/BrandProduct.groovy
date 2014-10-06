package ni.sb

class BrandProduct extends Product {
  List brands

  static constraints = {
    brands nullable:false
  }

  static hasMany = [brands:Brand]

  static mapping = {
  	brands cascade: "all-delete-orphan"
  }

  String toString() { name }
}
