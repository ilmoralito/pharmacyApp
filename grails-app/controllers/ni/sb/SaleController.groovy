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
                List<Item> items = Item.list()
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

        [sales: sales]
    }

    def detail(Long id) {
        Sale sale = Sale.get(id)

        if (!sale) {
            response.sendError 404
        }

        [sale: sale]
    }

    def summary() {

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
    String toName
    BigDecimal moneyReceived
    String annotation
    Employee employee
    Boolean canceled = false

    static constraints = {
        importFrom Sale, exclude: ["user"]
    }
}
