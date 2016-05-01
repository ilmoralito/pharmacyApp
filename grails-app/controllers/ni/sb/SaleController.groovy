package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
    def springSecurityService

    static defaultAction = "create"
    static allowedMethods = [
        create: ["GET"],
        list: ["GET", "POST"],
        detail: "GET",
        cancelSale: "POST",
        summary: "GET"
    ]

    def create() {
        redirect action: "createSale"
    }

    def createSaleFlow = {
        init {
            action {
                List<Item> items = Item.list().unique() { a, b -> a.product.name <=> b.product.name }.sort { it.product.name }
                List<SaleDetail> saleDetails = []

                [items: items, saleDetails: saleDetails, clientFormState: "hide"]
            }

            on("success").to "sale"
        }

        sale {
            on("chooseItems") {
                Item item = Item.get(params.int("id"))
                def query = Item.where {
                    product.name == item.product.name
                }
                List<Item> data = query.list()

                [data: data, item: item]
            }.to "sale"

            on("addItem") { SaleDetailCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                // Check if current items exists in saleDetails
                SaleDetail a = flow.saleDetails.find { saleDetail ->
                    saleDetail.item == cmd.item
                }

                if (a) {
                    a.quantity = cmd.quantity
                    a.total = cmd.item.sellingPrice * cmd.quantity
                } else {
                    SaleDetail saleDetail = new SaleDetail(
                        item: cmd.item,
                        quantity: cmd.quantity,
                        total: cmd.item.sellingPrice * cmd.quantity
                    )

                    flow.saleDetails << saleDetail
                }
            }.to "sale"

            on("removeItem") {
                Integer index = params.int("id")

                flow.saleDetails.remove(index)
            }.to "sale"

            on("addClient") { ClientCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "A ocurrido un error"
                    flow.clientFormState = "show"
                    return error()
                }

                Client client = new Client(
                    fullName: cmd.fullName,
                    email: cmd.email,
                    address: cmd.address,
                    telephoneNumber: cmd.telephoneNumber
                )

                flow.clientFormState = "hide"
                client.save(flush: true)
            }.to "sale"

            on("confirm") { SaleCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                Sale sale = new Sale(
                    user: springSecurityService.currentUser,
                    balance: cmd.balance,
                    client: cmd.client,
                    moneyReceived: cmd.moneyReceived,
                    annotation: cmd.annotation,
                    employee: cmd.employee
                )

                flow.saleDetails.each { saleDetail ->
                    saleDetail.item.quantity -= saleDetail.quantity

                    sale.addToSaleDetails(saleDetail)
                }

                if (!sale.save()) {
                    sale.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                flash.message = "Venta realizada correctamente"
            }.to "done"
        }

        done {
            redirect action: "create"
        }
    }

    def list() {
        Date today = new Date()
        User currentUser = springSecurityService.currentUser
        List<Sale> sales = Sale.fromTo(today, today).findAllByUser(currentUser)

        [sales: sales.sort { a, b -> b.dateCreated <=> a.dateCreated }]
    }

    def detail(Long id) {
        Sale sale = Sale.get(id)

        if (!sale) {
            response.sendError 404
        }

        [sale: sale]
    }

    def cancelSale(Long id) {
        Sale sale = Sale.get(id)

        if (!sale) {
            response.sendError 404
        }

        sale.canceled = true
        sale.reazonOfCanelation = params?.reazonOfCanelation

        if (!sale.save()) {
            sale.errors.allErrors.each { error ->
                log.error "[field: $error, defaultMessage: $error.defaultMessage]"
            }

            flash.message = "A ocurrido un error. Motivo de la anulacion es un dato obligatorio"

            redirect action: "detail", id: id
            return
        }

        flash.message = "Venta anulada"

        redirect action: "detail", id: id
    }

    def summary() {
        Date today = new Date()
        User user = springSecurityService.currentUser
        List<SaleDetail> data = SaleDetail.fromTo(today, today).bySaleUser(user).list()
        BigDecimal balance = Sale.fromTo(today, today).findAllByUser(user).balance.sum() ?: 0.0
        BigDecimal expenseBalance = Expense.fromTo(today, today).findAllByUser(user).quantity.sum() ?: 0.0
        List<Map> saleDetails = data.groupBy { it.item }.collect { a ->
            [
                item: a.key,
                quantity: a.value.quantity.sum()
            ]
        }.sort { -it.quantity }

        [
            saleDetails: saleDetails,
            balance: balance,
            expenseBalance: expenseBalance
        ]
    }
}

class SaleCommand {
    BigDecimal balance
    Client client
    BigDecimal moneyReceived
    String annotation
    Employee employee
    Boolean canceled = false

    static constraints = {
        importFrom Sale, exclude: ["user"]
    }
}

class SaleDetailCommand {
    Item item
    Integer quantity

    static constraints = {
        importFrom SaleDetail
    }
}

class ClientCommand {
    String fullName
    String email
    String address
    String telephoneNumber

    static constraints = {
        importFrom Client
    }
}
