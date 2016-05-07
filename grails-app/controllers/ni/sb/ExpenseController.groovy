package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ExpenseController {
    def springSecurityService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        create: "POST",
        delete: "GET"
    ]

    def list() {
        Boolean filtered = request.method == "POST"

        Closure expenses = {
            Date today = new Date()

            if (request.get) {
                Expense.fromTo(today, today).list()
            } else {
                Date fromDate = params.date("from", "yyyy-MM-dd") ?: today
                Date toDate = params.date("to", "yyyy-MM-dd") ?: today
                List users = params.list("users")*.toLong()

                if (users) {
                    Expense.fromTo(fromDate, toDate).byUser(users).list()
                } else {
                    Expense.fromTo(fromDate, toDate).list()
                }
            }
        }

        [expenses: expenses(), filtered: filtered]
    }

    def create() {
        User user = springSecurityService.currentUser
        params.user = user
        Expense expense = new Expense(params)

        if (!expense.save()) {
            expense.errors.allErrors.each { error ->
                log.error "[field: $error.field, message: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error."
        }

        redirect action: "list"
    }

    @Secured(["ROLE_ADMIN"])
    def delete(Long id) {
        Expense expense = Expense.get id

        if (!expense) {
            response.sendError 404
        }

        expense.delete()

        redirect action: "list"
    }
}
