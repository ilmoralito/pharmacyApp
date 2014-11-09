package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class DailyController {
	static defaultAction = "list"
	static allowedMethods = [
		list:["GET", "POST"],
		addExpense:"POST"
	]

  def list() {
 		def date = (request.method == "POST" && params?.date) ? params.date("date", "yyyy-MM-dd") : new Date()
  	def daily = Daily.fromTo(date, date).get()

  	def dateCriteria = Daily.createCriteria()
  	def dates = dateCriteria.list {
  		projections {
  			groupProperty "date"
  		}
  	}

  	[daily:daily, dates:dates*.format("yyyy-MM-dd")]
  }

	def addExpense(ExpensesCommand command) {
		if (command.hasErrors()) {
			flash.message = "Datos incorrectos"
		} else {
			def today = new Date()
			def daily = Daily.fromTo(today, today).get()
			def expenses = new Expenses(description:command.description, quantity:command.quantity)

			daily.addToExpenses expenses

			daily.save()
		}

		redirect action:"list"
	}
}

class ExpensesCommand {
	String description
  BigDecimal quantity

  static constraints = {
		importFrom Expenses
  }
}
