package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ReportController {
    static defaultAction = "sales"
    static allowedMethods = [
        sales: ["GET", "POST"],
        stock: ["GET", "POST"],
        clients: ["GET", "POST"],
        employees: ["GET", "POST"]
    ]

    def sales() {

    }

    def stock() {

    }

    def clients() {

    }

    def employees() {

    }
}
