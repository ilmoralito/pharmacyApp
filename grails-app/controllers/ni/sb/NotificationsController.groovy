package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class NotificationsController {
    def generalService
    def exportService
    def grailsApplication

    static defaultAction = "quantity"
    static allowedMethods = [
        quantity: "GET",
        expire: "GET",
        expired: "GET",
        pendingOrders: "GET",
        clientPayments: "GET"
    ]

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def quantity() {

    }

    def expire() {

    }

    def expired() {

    }

    def pendingOrders() {
        
    }

    def clientPayments() {

    }
}
