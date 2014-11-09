package ni.sb

import groovy.transform.ToString

@ToString
class Expenses {
	String description
  BigDecimal quantity

	Date dateCreated

  static constraints = {
    description blank:false
    quantity nullable:false, min:0.1
  }

  static mapping = {
  	version false
  }

  static belongsTo = [daily:Daily]
}
