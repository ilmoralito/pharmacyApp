package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class NotificationsController {
    def itemService

    static defaultAction = "quantity"
    static allowedMethods = [
        lowStocks: "GET",
        aboutToExpire: "GET",
        paymentDateClose: "GET",
    ]

    def lowStocks() {
        List<Item> items = itemService.getItemsWithlowStocks()

        [items: items]
    }

    def aboutToExpire() {

    }

    def paymentDateClose() {

    }
}
