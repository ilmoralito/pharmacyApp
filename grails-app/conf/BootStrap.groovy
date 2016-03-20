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

        // DEALER
        List<Distributor> dealers = []
        dealers << builder.distributor(name: "Dealer1", telephoneNumber: "23114455")
        dealers << builder.distributor(name: "Dealer2", telephoneNumber: "23111234")
        dealers << builder.distributor(name: "Dealer3", telephoneNumber: "23117788")
        dealers << builder.distributor(name: "Dealer4", telephoneNumber: "23118745")
        dealers << builder.distributor(name: "Dealer5", telephoneNumber: "23112258")

        dealers.each { dealer ->
            dealer.save failOnError: true
        }

        assert Distributor.count() == 5

        // PROVIDER
        List<Provider> providers = []

        providers << builder.provider(
            name: "Provider1",
            address: "Lorem ipsum dolor sit ament",
            telephoneNumber: "23114455"
        )

        providers << builder.provider(
            name: "Provider2",
            address: "Lorem ipsum dolor sit amext",
            telephoneNumber: "23113355"
        )

        providers << builder.provider(
            name: "Provider3",
            address: "Lorem ipsum dolor sit amelt",
            telephoneNumber: "23884455"
        )

        providers << builder.provider(
            name: "Provider4",
            address: "Lorem ipsum dolor sit amept",
            telephoneNumber: "23994455"
        )

        providers << builder.provider(
            name: "Provider5",
            address: "Lorem ipsum dolor sit ameet",
            telephoneNumber: "23994451",
            status: false
        )

        providers.each { provider ->
            provider.save failOnError: true
        }

        assert Provider.count() == 5

        // CLIENT
        List<Client> clients = []

        clients << builder.client(
            fullName: "client one",
            address: "Lorem ipsum dolor sit amet",
            identificationCard: "2312901800001w"
        ) {
            telephone(telephoneNumber: "23114455")
            telephone(telephoneNumber: "23114433")
            telephone(telephoneNumber: "23114458")
        }

        clients << builder.client(
            fullName: "client two",
            address: "Lorem ipsum dolor sit amit",
            identificationCard: "2312901800002w"
        ) {
            telephone(telephoneNumber: "23114423")
        }

        clients << builder.client(
            fullName: "client three",
            address: "Lorem ipsum dolor sit amat",
            identificationCard: "2312901800003w"
        ) {
            telephone(telephoneNumber: "23112333")
        }

        clients << builder.client(
            fullName: "client four",
            address: "Lorem ipsum dolor sit ania",
            identificationCard: "2312901800004w"
        ) {
            telephone(telephoneNumber: "23111233")
            telephone(telephoneNumber: "12345678")
            telephone(telephoneNumber: "87654321")
            telephone(telephoneNumber: "11112222")
        }

        clients << builder.client(
            fullName: "client five",
            address: "Lorem ipsum dolor sit naruto",
            identificationCard: "2312901800005w"
        ) {
            telephone(telephoneNumber: "12344321")
        }

        clients.each { client ->
            client.save failOnError: true
        }

        assert Client.count() == 5

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
