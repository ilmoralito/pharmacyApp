package ni.sb

import org.grails.databinding.BindUsing

class Brand {
  @BindUsing({obj, source ->
    source['name']?.capitalize()
  })
  String name

  Date dateCreated

  static constraints = {
    name blank:false, unique:"brandProduct"
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

  static mapping = {
    version false
    sort dateCreated: "desc"
  }

  String toString() { name }
}
