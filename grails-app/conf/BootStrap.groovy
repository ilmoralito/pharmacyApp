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
            password: "password",
            email: "admin@domain.com",
            fullName: "admin user"
        ).save failOnError: true

        User user = new User(
            password: "password",
            email: "user@domain.com",
            fullName: "user user"
        ).save failOnError: true

        User test = new User(
            password: "temporal",
            email: "test@domain.com",
            fullName: "test test"
        ).save failOnError: true

        Role adminRole = new Role(authority: "ROLE_ADMIN").save failOnError: true
        Role userRole = new Role(authority: "ROLE_USER").save failOnError: true

        UserRole.create admin, adminRole, true
        UserRole.create user, userRole, true
        UserRole.create test, userRole, true

        assert User.count() == 3
        assert Role.count() == 2
        assert UserRole.count() == 3

        // DEALER
        List<Distributor> dealers = []
        dealers << builder.distributor(
            name: "Dealer1",
            telephoneNumber: "23114455"
        ) {
            contact(fullName: "contact1", email: "contact1@domain.com", telephoneNumber: "87878987")
        }

        dealers << builder.distributor(
            name: "Dealer2",
            telephoneNumber: "23111234"
        ) {
            contact(fullName: "contact2", email: "contact2@domain.com", telephoneNumber: "67765654")
        }

        dealers << builder.distributor(
            name: "Dealer3",
            telephoneNumber: "23117788"
        ) {
            contact(fullName: "contact3", email: "contact3@domain.com", telephoneNumber: "76676545")
        }

        dealers << builder.distributor(
            name: "Dealer4",
            telephoneNumber: "23118745"
        ) {
            contact(fullName: "contact4", email: "contact4@domain.com", telephoneNumber: "56764576")
        }

        dealers << builder.distributor(
            name: "Dealer5",
            telephoneNumber: "23112258"
        ) {
            contact(fullName: "contact5", email: "contact5@domain.com", telephoneNumber: "76565434")
        }

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

        // COMPANY
        List<Company> companies = []

        companies << builder.company(
            name: "company1",
            city: "Leon",
            address: "Lorem ipsum dolor sit amet",
            telephoneNumber: "23114455",
            contactFullName: "contact1 name",
            contactTelephoneNumber: "88776678",
            contactEmail: "contact1.name@company1.com.ni",
            enabled: true
        ) {
            employee(fullName: "employee1", inss: "123456789")
            employee(fullName: "employee2", inss: "123456788")
            employee(fullName: "employee3", inss: "623456787")
            employee(fullName: "employee4", inss: "523456787")
            employee(fullName: "employee5", inss: "423456787")
            employee(fullName: "employee6", inss: "323456787")
        }

        companies << builder.company(
            name: "company2",
            city: "Chinandega",
            address: "Lorem ipsum dolor sit amot",
            telephoneNumber: "23124455",
            contactFullName: "contact2 name",
            contactTelephoneNumber: "76776678",
            contactEmail: "contact2.name@company1.com.ni",
            enabled: true
        ) {
            employee(fullName: "employee7", inss: "123346789")
            employee(fullName: "employee8", inss: "123566788")
            employee(fullName: "employee9", inss: "623676787")
            employee(fullName: "employee0", inss: "323676787")
        }

        companies.each { company ->
            company.save failOnError: true
        }

        assert Company.count() == 2

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
