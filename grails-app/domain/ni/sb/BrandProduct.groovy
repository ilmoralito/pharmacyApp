package ni.sb

class BrandProduct extends Product {
	String brand
  String detail
	
  static constraints = {
    brand blank:false
    detail blank:false
  }

  String toString() { brand }
}
