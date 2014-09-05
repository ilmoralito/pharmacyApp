package ni.sb

import org.grails.databinding.BindUsing

class Product {
  @BindUsing({ obj, source ->
    source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
  })
	String name
  String code

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    code blank:false, unique:true
  }

  static mapping = {
  	version false
  }

  static belongsTo = [provider:Provider]
  static hasMany = [presentations:Presentation]

  String toString() { "$name, $code" }
}
