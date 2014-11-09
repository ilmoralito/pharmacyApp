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

  static namedQueries = {
    fromTo { from, to ->
      ge "date", from.clearTime()
      le "date", to.clearTime() + 1
    }
  }

  static hasMany = [expenses:Expenses]
}
