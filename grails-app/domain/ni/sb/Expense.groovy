package ni.sb

import groovy.transform.ToString

@ToString
class Expense {
    def springSecurityService

    String description
    BigDecimal quantity
    User user

    Date dateCreated
    Date lastUpdated

    static constraints = {
        description blank: false
        quantity min: 0.1
    }

    static namedQueries = {
        fromTo { from, to ->
            ge "dateCreated", from.clearTime()
            le "dateCreated", to.clearTime() + 1
        }

        byUser { users ->
            user {
                "in" "id", users
            }
        }
    }

    static mapping = {
        sort dateCreated: "asc"
    }
}
