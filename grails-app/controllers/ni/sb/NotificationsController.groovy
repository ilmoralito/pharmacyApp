package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class NotificationsController {
    static defaultAction = "quantity"
    static allowedMethods = [
        lowStocks: "GET",
        aboutToExpire: "GET",
        paymentDateClose: "GET",
    ]

    def lowStocks() {

    }

    def aboutToExpire() {

    }

    def paymentDateClose() {

    }
}
