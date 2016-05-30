package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PaymentController {
    static defaultAction = ""
    static allowedMethods = [
        index: ["GET", "POST"]
    ]

    def index() {
        List<CreditSale> creditSales = []

        if (request.post) {
            String q = params?.q?.trim()

            creditSales = CreditSale.createCriteria().list {
                eq "canceled", false
                or {
                    eq "invoiceNumber", q
                    employee {
                        or {
                            like "fullName", "%$q%"
                            eq "inss", q
                            eq "identificationCard", q
                            company {
                                like "name", "%$q%"
                            }
                        }
                    }
                }
            }
        }

        [creditSales: creditSales]
    }
}
