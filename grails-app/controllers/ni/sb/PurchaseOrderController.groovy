package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {

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
                List<Distributor> dealers = Distributor.where {
                    providers.size() > 0 && enabled == true
                }.list()

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

                Distributor dealer = Distributor.get(command.distributor)

                if (!distributor) {
                    return error()
                }

                println distributor

                return error()
            }. to "items"

            on("cancel").to "done"
        }

        items {

        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }
}

class createPurchaseOrderCommand {
    Distributor distributor
    String paymentType
    String invoiceNumber

    static constraints = {
        importFrom PurchaseOrder
    }
}
