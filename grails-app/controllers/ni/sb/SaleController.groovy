package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
    def springSecurityService
    def saleDetailService
    def saleService
    def expenseService

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

                [items: items, saleDetails: saleDetails, clientFormState: "hide", saleType: "cash"]
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
                flow.clientID = client.id
            }.to "sale"

            on("confirmCashSale") { CashSaleCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                CashSale cashSale = new CashSale(
                    user: springSecurityService.currentUser,
                    client: cmd.client,
                    balance: cmd.balance,
                    moneyReceived: cmd.moneyReceived,
                    annotation: cmd.annotation
                )

                flow.saleDetails.each { saleDetail ->
                    saleDetail.item.quantity -= saleDetail.quantity

                    cashSale.addToSaleDetails(saleDetail)
                }

                if (!cashSale.save()) {
                    cashSale.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                flash.message = "Venta realizada correctamente"
            }.to "done"

            on("confirmCreditSale") { CreditSaleCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                CreditSale creditSale = new CreditSale(
                    user: springSecurityService.currentUser,
                    invoiceNumber: cmd.invoiceNumber,
                    employee: cmd.employee,
                    balance: cmd.balance,
                    annotation: cmd.annotation
                )

                flow.saleDetails.each { saleDetail ->
                    saleDetail.item.quantity -= saleDetail.quantity

                    creditSale.addToSaleDetails(saleDetail)
                }

                if (!creditSale.save()) {
                    creditSale.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "Datos incorrectos"
                    return error()
                }

                flash.message = "Venta realizada correctamente"
            }.to "done"

            on("changeSaleType") {
                flow.saleType = params?.saleType ?: "cash"
            }.to "sale"
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

        // Return sale items quantity to inventary
        sale.saleDetails.each { saleDetail ->
            saleDetail.item.quantity += saleDetail.quantity
        }

        flash.message = "Venta anulada exitosamente y productos retornados a inventario"
        redirect action: "detail", id: id
    }

    def summary() {
        Date today = new Date()

        List<SaleDetail> saleDetails = saleDetailService.getSaleDetails(today, today)
        List<CashSale> cashSales = saleService.getCashSales(today, today)
        List<CreditSale> creditSales = saleService.getCreditSales(today, today)
        List<CashSale> canceledCashSales = saleService.getCashSales(today, today, true)

        [
            balanceCashSales: saleService.getBalanceSummary(cashSales),
            quantityCashSales: cashSales.size(),
            quantityCreditSales: creditSales.size(),
            quantityCanceledSales: canceledCashSales.size(),
            balanceCreditSales: saleService.getBalanceSummary(creditSales),
            balanceCanceledSales: saleService.getBalanceSummary(canceledCashSales),
            expenseBalance: expenseService.getExpensesBalanceSummary(today, today),
            saleDetails: saleDetailService.getSaleDetailSummary(saleDetails),
            companies: creditSales.groupBy { it.employee.company.name }.collect { a ->
                [
                    name: a.key,
                    balance: a.value.balance.sum(),
                    size: a.value.size()
                ]
            }
        ]
    }
}

class CashSaleCommand {
    Client client
    BigDecimal balance
    BigDecimal moneyReceived
    String annotation

    static constraints = {
        importFrom CashSale
    }
}

class CreditSaleCommand {
    String invoiceNumber
    Employee employee
    BigDecimal balance
    String annotation

    static constraints = {
        importFrom CreditSale
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
