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

    def list(String from, String to) {
        Boolean filtered = request.method == "POST"

        Closure expenses = {
            Date fromDate = params.date("from", "yyyy-MM-dd") ?: new Date()
            Date toDate = params.date("to", "yyyy-MM-dd") ?: new Date()

            if (request.method == "GET") {
                Expense.fromTo(fromDate, toDate).list()
            } else {
                List users = params.list("users")*.toLong()

                Expense.fromTo(fromDate, toDate).byUser(users).list()
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

            flash.message = "A ocurrido un error. Intentalo otravez"
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
