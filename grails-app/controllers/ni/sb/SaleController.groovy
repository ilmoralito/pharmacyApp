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

                [items: items, saleDetails: saleDetails]
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
                    toName: cmd.toName,
                    moneyReceived: cmd.moneyReceived,
                    annotation: cmd.annotation,
                    employee: cmd.employee
                )

                flow.saleDetails.each { saleDetail ->
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
        List<Sale> sales = Sale.fromTo(today, today + 1).findAllByUser(currentUser)

        [sales: sales.sort { a, b -> b.dateCreated <=> a.dateCreated }]
    }

    def detail(Long id) {
        Sale sale = Sale.get(id)

        if (!sale) {
            response.sendError 404
        }

        [sale: sale]
    }

    def summary() {
        Date today = new Date()
        User currentUser = springSecurityService.currentUser
        def c = SaleDetail.createCriteria()
        List<SaleDetail> data = c {
            ge "dateCreated", today.clearTime()
            le "dateCreated", today.clearTime() + 1
            sale {
                eq "user", currentUser
            }
        }

        BigDecimal balance = Sale.fromTo(today, today + 1).findAllByUser(currentUser).balance.sum()

        BigDecimal expenseBalance = Expense.createCriteria().list {
            ge "dateCreated", today.clearTime()
            le "dateCreated", today.clearTime() + 1
            eq "user", currentUser
        }.quantity.sum()

        List saleDetails = data.groupBy { it.item }.collect { a ->
            [
                item: a.key,
                quantity: a.value.size()
            ]
        }.sort { -it.quantity }

        [
            saleDetails: saleDetails,
            balance: balance ?: 0,
            expenseBalance: expenseBalance ?: 0
        ]
    }
}

class SaleDetailCommand {
    Item item
    Integer quantity

    static constraints = {
        importFrom SaleDetail
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
