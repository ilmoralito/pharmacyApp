package ni.sb

import grails.transaction.Transactional

@Transactional
class ExpenseService {

    def getExpensesBalanceSummary(Date from, Date to) {
        List<Expense> expenses = Expense.createCriteria().list {
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }

        expenses?.quantity?.sum() ?: 0.0
    }
}
