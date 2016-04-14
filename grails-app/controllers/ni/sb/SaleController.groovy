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
        String query = params?.query
        def criteria = Item.createCriteria()
        def result = criteria {
            product {
                like "name", "%1%"
            }
        }

        List items = result.collect { item ->
            [
                id: item.id,
                name: item.product.name,
                quantity: item.quantity,
                purchasePrice: item.purchasePrice,
                sellingPrice: item.sellingPrice,
                provider: item.product.provider.name,
                presentation: item instanceof MedicineOrder ? item.presentation.name : null,
                measure: item instanceof MedicineOrder ? item.measure.name : null,
                brand: item instanceof BrandProductOrder ? item.brand.name : null,
                detail: item instanceof BrandProductOrder ? item.detail.name : null
            ]
        }

        render(contentType: "application/json") {
            items
        }
    }

    def list() {
        Date today = new Date()

        List<Sale> sales = Sale.fromTo(today, today).byCurrentUser().list()

        [sales: sales]
    }

}