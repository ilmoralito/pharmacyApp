import ni.sb.*
import grails.util.Environment
import grails.util.DomainBuilder
import grails.util.Holders

class BootStrap {
    def itemService

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            development()
        }
    }

    def destroy = {}

    private development() {
        DomainBuilder builder = new DomainBuilder()
        builder.classNameResolver = "ni.sb"

        // USERS
        User admin = new User(
            username: "admin",
            password: "password",
            email: "admin@domain.com",
            fullName: "admin user"
        ).save failOnError: true

        User user = new User(
            username: "user",
            password: "password",
            email: "user@domain.com",
            fullName: "user user"
        ).save failOnError: true

        Role adminRole = new Role(authority: "ROLE_ADMIN").save failOnError: true
        Role userRole = new Role(authority: "ROLE_USER").save failOnError: true

        UserRole.create admin, adminRole, true
        UserRole.create user, userRole, true

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2

        // EXPENSE
        new Expense(description: "Lorem ipsum dolor sit amet", quantity: 10, user: user).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amot", quantity: 40, user: user).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amat", quantity: 30, user: user).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: user).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: admin).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: admin).save failOnError: true

        assert Expense.count() == 6
    }

    private production() {
        // TODO
    }
}
