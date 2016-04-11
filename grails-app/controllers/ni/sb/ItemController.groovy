package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ItemController {
    static defaultAction = "stock"
    static allowedMethods = [
        stock: ["GET", "POST"]
    ]

    def stock() {
        Closure items = {
            if (request.post) {
                
            } else {
                Item.stock().list()
            }
        }

        [items: items()]
    }
}
