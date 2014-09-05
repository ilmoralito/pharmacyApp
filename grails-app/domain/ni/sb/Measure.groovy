package ni.sb

import grails.util.Holders

class Measure {
	String name

	Date dateCreated
	Date lastUpdated

  static constraints = {
    name blank:false, maxSize:255, validator: { name, measure ->
      name in Holders.config.ni.sb.presentationsAndMeasures[measure.presentation.name]
    }
  }

  static mapping = {
  	version false
  }

  static belongsTo = [presentation:Presentation]

  String toString() { measure }
}
