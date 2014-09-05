package ni.sb

import grails.util.Holders

class Presentation {
  String name	

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, maxSize:255, inList:Holders.config.ni.sb.presentationsAndMeasures.keySet() as List
  }

  static mapping = {
  	version false
  }

  static belongsTo = [product:Product]
  static hasMany = [measures:Measure]

  String toString() { name }
}
