package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ReportsController {
	static defaultAction = "sales"
	static allowedMethods = [
		sales:["GET", "POST"]
	]

  def sales() {
  	def from = params.date("from", "yyyy-MM-dd") ?: new Date()
  	def to = params.date("to", "yyyy-MM-dd") ?: new Date()

    //This does the job but needs to be improved
    def results = (from..to).collectEntries { date ->
      [(date.format("yyyy-MM-dd")):
        [
          "salesAmount":Sale.fromTo(date, date + 1).findAllByCanceled(false).balance.sum(),
          "expensesDailyAmount":Daily.fromTo(date, date).get()?.expenses?.quantity?.sum()
        ]
      ]
    }
    
    [results:results]
  }
}
