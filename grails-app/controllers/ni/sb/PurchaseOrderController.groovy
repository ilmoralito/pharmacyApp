package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
    def springSecurityService
    def distributorService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"]
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
            on("confirm") { createPurchaseOrderCommand command ->
                if (command.hasErrors()) {
                    command.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                println command.distributor
                println command.distributor.class.name

                User currentUser = springSecurityService.currentUser


                /*
                if (!distributor) {
                    return error()
                }
                */

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
    Distributor distributor
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}
