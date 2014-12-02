package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true)
class Distributor {
  @BindUsing({
    obj, source -> source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
  })
	String name

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    telephones minSize:1
  }

  static mapping = {
  	version false
  }

  static hasMany = [telephones:String]
}
