package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DailyController {
	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"]
	]

  def list() {
  	def dailyExpensesCriteria = Daily.createCriteria()
  	def dailyExpenses = dailyExpensesCriteria.get {
  		def today = new Date()

  		ge "date", today.clearTime()
  		le "date", today.clearTime() + 1
  	}

  	def dateCriteria = Daily.createCriteria()
  	def dates = dateCriteria.list {
  		projections {
  			groupProperty "date"
  		}
  	}

  	[dailyExpenses:dailyExpenses, dates:dates*.format("yyyy-MM-dd")]
  }
}
