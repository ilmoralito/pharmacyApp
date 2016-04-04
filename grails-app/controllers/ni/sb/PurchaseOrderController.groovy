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
                println cmd.distributor
                println cmd.invoiceNumber
                println cmd.paymentDate
                println cmd.paymentType

                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                [
                    distributor: cmd.distributor,
                    invoiceNumber: cmd.invoiceNumber,
                    paymentDate: cmd.paymentDate,
                    paymentType: cmd.paymentType
                ]
            }. to "items"

            on("cancel").to "done"
        }

        items {
            on("show").to "show"
        }

        show {
            on("confirm") {

            }.to "show"

            on("goBack").to "items"
        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }
}

class PurchaseOrderCommand implements Serializable {
    Integer distributor
    String invoiceNumber
    Date paymentDate
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}
