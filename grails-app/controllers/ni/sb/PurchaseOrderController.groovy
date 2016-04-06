package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
    def springSecurityService
    def distributorService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        create: "GET"
    ]

    def list(String paymentStatus) {
        Closure purchaseOrders = {
            if (paymentStatus == null) {
                paymentStatus = "pending"
            }

            PurchaseOrder.findAllByPaymentStatus(paymentStatus)
        }

        [purchaseOrders: purchaseOrders()]
    }

    def create() {
        redirect action: "createPurchaseOrder"
    }

    def createPurchaseOrderFlow = {
        init {
            action {
                List<Distributor> dealers = distributorService.getValidDistributors()

                [dealers: dealers]
            }

            on("success").to "selectPurchaseOrderParameters"
        }


        selectPurchaseOrderParameters {
            on("confirm") { PurchaseOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Distributor distributor =distributorService.getDistributor(cmd.distributor)

                // distributor > providers > product
                List<Product> products = []

                distributor.providers.each { provider ->
                    products << provider.products
                }

                [
                    distributor: distributor,
                    invoiceNumber: cmd.invoiceNumber,
                    paymentDate: getPaymentDate(cmd.paymentType, distributor.daysToPay),
                    paymentType: cmd.paymentType,
                    productList: products.flatten(),
                    products: products.flatten().unique() { a, b -> a.name <=> b.name }
                ]
            }. to "items"

            on("cancel").to "done"
        }

        items {
            on("show").to "show"

            on("query") {
                String q = params?.q

                List<Product> result = flow.productList.findAll { it.name == q }

                [result: result, q: q]
            }.to "items"
        }

        show {
            on("confirm") { PurchaseOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Distributor distributor =distributorService.getDistributor(cmd.distributor)

                flow.distributor = distributor
                flow.invoiceNumber = cmd.invoiceNumber
                flow.paymentDate = getPaymentDate(cmd.paymentType, distributor.daysToPay)
                flow.paymentType = cmd.paymentType
            }.to "show"

            on("goBack").to "items"
        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }

    private getPaymentDate(String paymentType, Integer daysToPay) {
        Date today = new Date()
        Date paymentDate = null

        if (paymentType == "credit") {
            paymentDate = today + daysToPay
        }

        paymentDate
    }
}

class PurchaseOrderCommand implements Serializable {
    Integer distributor
    String invoiceNumber
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}
