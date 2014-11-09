package ni.sb

import groovy.transform.ToString

@ToString
class Daily {
	Date date
  List expenses

  static constraints = {
    date nullable:false, unique:true
    expenses nullable:false
  }

  static hasMany = [expenses:Expenses]
}
