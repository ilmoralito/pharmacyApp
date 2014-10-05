package ni.sb

import org.grails.databinding.BindUsing

class Brand {
  @BindUsing({obj, source ->
    source['name']?.capitalize()
  })
  String name
  List details

  static constraints = {
    name blank:false
  }

  static namedQueries = {
  	distinctBrands {
  		projections {
        groupProperty "name"
      }
  	}
  }

  static belongsTo = [brandProduct:BrandProduct]
  static hasMany = [details:String]

  String toString() { name }
}
