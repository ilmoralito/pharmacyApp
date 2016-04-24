package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class NotificationsController {
    static defaultAction = "quantity"
    static allowedMethods = [
        quantity: "GET",
        expire: "GET",
        expire: "GET",
    ]

    def lowExistences() {

    }

    def aboutToDueDate() {

    }

    def dueDate() {

    }
}
