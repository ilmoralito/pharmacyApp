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
                def currentUser = springSecurityService.principal
                List<Distributor> dealers = distributorService.getValidDistributors()

                [dealers: dealers, currentUser: currentUser]
            }

            on("success").to "selectPurchaseOrderParameters"
        }


        selectPurchaseOrderParameters {
            on("confirm") { createPurchaseOrderCommand command ->
                if (command.hasErrors()) {
                    command.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Distributor distributor = Distributor.get(command.distributor)

                [
                    invoiceNumber: command.invoiceNumber,
                    paymentType: command.paymentType,
                    deadline: command.deadline,
                    distributor: distributor
                ]
            }. to "items"

            on("cancel").to "done"
        }

        items {
            on("show").to "show"
        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }
}

class createPurchaseOrderCommand {
    String invoiceNumber
    Date deadline
    Integer distributor
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}
