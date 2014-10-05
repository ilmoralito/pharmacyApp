package ni.sb

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Brand {
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

      resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
  	}
  }

  static belongsTo = [brandProduct:BrandProduct]
  static hasMany = [details:String]

  String toString() { name }
}
