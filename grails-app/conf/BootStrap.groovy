import ni.sb.*
import grails.util.Environment
import grails.util.DomainBuilder

class BootStrap {
    def grailsApplication

    def init = { servletContext ->
        ConfigObject config = grailsApplication.config.ni.sb

        config.lowStocks = 0
        config.aboutToExpire = 0
        config.paymentDateClose = 0

        // Set default values for configuration for all environments
        if (!Configuration.count()) {
            Configuration configuration = new Configuration(
                minLowStocks: config.minLowStocks,
                minMedicineOrdersAboutToExpire: config.minMedicineOrdersAboutToExpire,
                minPurchaseOrdersPaymentDateClose: config.minPurchaseOrdersPaymentDateClose
            )

            configuration.save failOnError: true
        }

        if (Environment.current == Environment.DEVELOPMENT) {
            development()
        }

        if (Environment.current == Environment.PRODUCTION) {
            production()
        }
    }

    def destroy = {}

    private development() {
        DomainBuilder builder = new DomainBuilder()
        builder.classNameResolver = "ni.sb"

        // CLIENT
        List<Client> clients = []

        clients << builder.client(
            fullName: "client one name",
            email: "client.one.name@domain.com",
            address: "client one name address",
            telephoneNumber: "23114455"
        )

        clients << builder.client(
            fullName: "client two name"
        )

        clients.each { client ->
            client.save failOnError: true
        }

        // USERS
        User admin = new User(
            fullName: "admin user",
            email: "admin@domain.com",
            telephoneNumber: "99887766",
            password: "password",
        ).save failOnError: true

        User user = new User(
            fullName: "user user",
            email: "user@domain.com",
            telephoneNumber: "88776655",
            password: "password"
        ).save failOnError: true

        User test = new User(
            fullName: "test user",
            email: "test@domain.com",
            telephoneNumber: "90897867"
        ).save failOnError: true

        Role adminRole = new Role(authority: "ROLE_ADMIN").save failOnError: true
        Role userRole = new Role(authority: "ROLE_USER").save failOnError: true

        UserRole.create admin, adminRole, true
        UserRole.create user, userRole, true
        UserRole.create test, userRole, true

        assert User.count() == 3
        assert Role.count() == 2
        assert UserRole.count() == 3

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
            10.times {
                employee(
                    fullName: "employee#$it",
                    inss: UUID.randomUUID().toString().replaceAll('-', '')[0..8]
                )
            }
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
            20.times {
                employee(
                    fullName: "employee#$it",
                    inss: UUID.randomUUID().toString().replaceAll('-', '')[0..8]
                )
            }
        }

        companies.each { company ->
            company.save failOnError: true
        }

        assert Company.count() == 2

        // MEASURE
        List<Measure> measures = []

        measures << builder.measure(name: "measure#1", id: "MEASURE#1")
        measures << builder.measure(name: "measure#2", id: "MEASURE#2")
        measures << builder.measure(name: "measure#3", id: "MEASURE#3")
        measures << builder.measure(name: "measure#4", id: "MEASURE#4")
        measures << builder.measure(name: "measure#5", id: "MEASURE#5")
        measures << builder.measure(name: "measure#6", id: "MEASURE#6")

        measures.each { measure ->
            measure.save failOnError: true
        }

        assert Measure.count() == 6

        // PRESENTATION
        List<Presentation> presentations = []

        presentations << builder.presentation(name: "presentation#1", id: "PRESENTATION#1") {
            measure(refId: "MEASURE#1")
            measure(refId: "MEASURE#2")
            measure(refId: "MEASURE#3")
        }
        presentations << builder.presentation(name: "presentation#2", id: "PRESENTATION#2") {
            measure(refId: "MEASURE#4")
            measure(refId: "MEASURE#5")
        }
        presentations << builder.presentation(name: "presentation#3", id: "PRESENTATION#3") {
            measure(refId: "MEASURE#1")
            measure(refId: "MEASURE#2")
        }
        presentations << builder.presentation(name: "presentation#4", id: "PRESENTATION#4") {
            measure(refId: "MEASURE#4")
            measure(refId: "MEASURE#5")
            measure(refId: "MEASURE#6")
        }

        presentations.each { presentation ->
            presentation.save failOnError: true
        }

        assert Presentation.count() == 4

        // DETAIL
        List<Detail> details = []

        details << builder.detail(name: "detail#1", id: "detail#1")
        details << builder.detail(name: "detail#2", id: "detail#2")
        details << builder.detail(name: "detail#3", id: "detail#3")
        details << builder.detail(name: "detail#4", id: "detail#4")
        details << builder.detail(name: "detail#5", id: "detail#5")

        details.each { detail ->
            detail.save failOnError: true
        }

        assert Detail.count() == 5

        // BRAND
        List<Brand> brands = []

        brands << builder.brand(name: "brand#1", id: "brand#1") {
            detail(refId: "detail#1")
            detail(refId: "detail#2")
            detail(refId: "detail#3")
        }
        brands << builder.brand(name: "brand#2", id: "brand#2") {
            detail(refId: "detail#3")
            detail(refId: "detail#4")
            detail(refId: "detail#5")
        }

        brands.each { brand ->
            brand.save failOnError: true
        }

        assert Brand.count() == 2

        // PROVIDER
        List<Provider> providers = []

        providers << builder.provider(
            name: "Laboratory1",
            address: "Lorem ipsum dolor sit ament",
            telephoneNumber: "23114455",
            id: "LABORATORY1"
        ) {
            4.times {
                product(name: "product#$it")
            }
            product(name: "product#4", enabled: false)
        }

        providers << builder.provider(
            name: "Laboratory2",
            address: "Lorem ipsum dolor sit amext",
            telephoneNumber: "23113355",
            id: "LABORATORY2"
        ) {
            (4..10).each { n ->
                product(name: "product#$n")
            }
        }

        providers << builder.provider(
            name: "Laboratory3",
            address: "Lorem ipsum dolor sit amelt",
            telephoneNumber: "23884455",
            id: "LABORATORY3"
        ) {
            product(name: "p1")
            product(name: "p2")
            product(name: "p3")
        }

        providers << builder.provider(
            name: "Laboratory4",
            address: "Lorem ipsum dolor sit amept",
            telephoneNumber: "23994455",
            id: "LABORATORY4"
        ) {
            product(name: "product#6")
            product(name: "product#7")
            product(name: "product#8")
        }

        providers << builder.provider(
            name: "Laboratory5",
            address: "Lorem ipsum dolor sit ameet",
            telephoneNumber: "23994451",
            enabled: false,
            id: "LABORATORY5"
        )

        providers.each { provider ->
            provider.save failOnError: true
        }

        assert Provider.count() == 5

        // DEALER
        List<Distributor> dealers = []
        dealers << builder.distributor(
            name: "Dealer1",
            telephoneNumber: "23114455",
            daysToPay: 30,
            id: "dealer#1"
        ) {
            provider(refId: "LABORATORY1")
            provider(refId: "LABORATORY2")
            provider(refId: "LABORATORY3")
            contact(fullName: "contact1", email: "contact1@domain.com", telephoneNumber: "87878987")
        }

        dealers << builder.distributor(
            name: "Dealer2",
            telephoneNumber: "23111234",
            daysToPay: 20,
            id: "dealer#2"
        ) {
            provider(refId: "LABORATORY1")
            provider(refId: "LABORATORY4")
            contact(fullName: "contact2", email: "contact2@domain.com", telephoneNumber: "67765654")
        }

        dealers << builder.distributor(
            name: "Dealer3",
            telephoneNumber: "23117788",
            daysToPay: 30
        ) {
            provider(refId: "LABORATORY3")
            provider(refId: "LABORATORY4")
            contact(fullName: "contact3", email: "contact3@domain.com", telephoneNumber: "76676545")
        }

        dealers << builder.distributor(
            name: "Dealer4",
            telephoneNumber: "23118745",
            daysToPay: 20
        ) {
            provider(refId: "LABORATORY1")
            provider(refId: "LABORATORY2")
            provider(refId: "LABORATORY3")
            provider(refId: "LABORATORY4")
            contact(fullName: "contact4", email: "contact4@domain.com", telephoneNumber: "56764576")
        }

        dealers << builder.distributor(
            name: "Dealer5",
            telephoneNumber: "23112258",
            daysToPay: 60,
            enabled: false
        ) {
            provider(refId: "LABORATORY3")
            contact(fullName: "contact5", email: "contact5@domain.com", telephoneNumber: "76565434")
        }

        dealers.each { dealer ->
            dealer.save failOnError: true
        }

        assert Distributor.count() == 5

        // EXPENSE
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: user).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: admin).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amit", quantity: 20, user: admin).save failOnError: true
        new Expense(description: "Lorem ipsum dolor sit amut", quantity: 5, user: test).save failOnError: true

        assert Expense.count() == 4

        // PURCHASEORDER
        /*
        List<PurchaseOrder> purchaseOrders = []
        Date today = new Date()

        purchaseOrders << builder.purchaseOrder(
            distributor(refId: "dealer#1"),
            user: admin,
            typeOfPurchase: "credit",
            invoiceNumber: "0001001",
            deadline: today + 60,
            balance: 5000, // TODO its needs to be calculated from total in items
            paymentStatus: "pending"
        )
        */
    }

    private production() {
        // TODO
    }
}
