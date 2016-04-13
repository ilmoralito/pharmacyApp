package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class SaleController {
    def springSecurityService

    static defaultAction = "create"
    static allowedMethods = [
        create: ["GET", "POST"],
        search: "POST",
        list: ["GET", "POST"]
    ]

    def create() { }

    def search() {
        render(contentType: "application/json") {
            message = "nami es mi amor"
        }
    }

    def list() {
        Date today = new Date()

        List<Sale> sales = Sale.fromTo(today, today).byCurrentUser().list()

        [sales: sales]
    }

}