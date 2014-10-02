package ni.sb

class Brand {
  String name
  List details

  static constraints = {
    name blank:false
  }

  
  static belongsTo = [brandProduct:BrandProduct]
  static hasMany = [details:String]

  String toString() {  brand }
}
