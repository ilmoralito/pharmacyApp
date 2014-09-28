package ni.sb

import grails.util.Holders

class Presentation implements Serializable {
  String name	

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, maxSize:255, inList:Holders.config.ni.sb.presentationsAndMeasures.keySet() as List
  }

  static mapping = {
  	version false
  }

  static belongsTo = [medicine:Medicine]

  List measures
  static hasMany = [measures:String]

  String toString() { name }
}
