package ni.sb

import org.grails.databinding.BindUsing

class Product implements Serializable {
  @BindUsing({ obj, source ->
    source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
  })
	String name
  String code
  Boolean status = true

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    code blank:false, unique:true
  }

  static mapping = {
  	version false
    sort dateCreated: "desc"
  }

  static belongsTo = [provider:Provider]
  static hasMany = [presentations:Presentation]

  String toString() { name }
}
