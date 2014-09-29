package ni.sb

import org.grails.databinding.BindUsing

class BrandProduct extends Product {
  @BindUsing({ obj, source ->
    source["brand"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
  })
	String brand

  @BindUsing({ obj, source ->
    source["detail"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
  })
  String detail
	
  static constraints = {
    brand blank:false
    detail blank:false
  }

  String toString() { brand }
}
