package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"]
    ]

    def list() {
        
    }
}
