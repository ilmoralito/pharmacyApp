package ni.sb

import org.grails.databinding.BindUsing
import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true)
class Distributor implements Serializable {
  @BindUsing({
    obj, source -> source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize()?.join(" ")
  })
	String name
  List telephones

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, unique:true
    telephones min:1, nullable:false
  }

  static mapping = {
  	version false
  }

  static hasMany = [telephones:String]
}
