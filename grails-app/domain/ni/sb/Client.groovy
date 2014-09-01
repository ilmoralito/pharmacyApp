package ni.sb

import org.grails.databinding.BindUsing

class Client {
  @BindUsing({ obj, source ->
    source["fullName"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
  })
  String fullName
	String address
  String identificationCard
  SortedSet phones 

	Date dateCreated
	Date lastUpdated

  static constraints = {
    fullName blank:false
    address blank:false
    identificationCard blank:false, unique:true
    phones nullable:true
  }

  static mapping = {
  	version false
  }

  static hasMany = [phones:String]

  String toString() { fullName }
}
