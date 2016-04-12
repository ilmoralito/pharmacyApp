package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
    def springSecurityService
    def distributorService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        create: "GET",
        show: "GET"
    ]

    def list() {
        Closure purchaseOrders = {
            if (request.post) {
                String invoiceNumber = params?.invoiceNumber
                Date from = params.date("from", "yyyy-MM-dd")
                Date to = params.date("to", "yyyy-MM-dd")
                List<Integer> distributors = params.list("distributor")*.toLong()
                List<Integer> users = params.list("users")*.toLong()
                List<String> paymentType = params.list("paymentType").toList()
                List<String> paymentStatus = params.list("paymentStatus").toList()

                PurchaseOrder.filter(distributors, from, to, users, invoiceNumber, paymentType, paymentStatus).list()
            } else {
                PurchaseOrder.findAllByPaymentStatus("pending")
            }
        }

        [purchaseOrders: purchaseOrders()]
    }

    def create() {
        redirect action: "createPurchaseOrder"
    }

    def show(Long id) {
        PurchaseOrder purchaseOrder = PurchaseOrder.get(id)

        if (!purchaseOrder) {
            response.sendError 404
        }

        [purchaseOrder: purchaseOrder]
    }

    def createPurchaseOrderFlow = {
        init {
            action {
                List<Distributor> dealers = distributorService.getValidDistributors()

                [dealers: dealers]
            }

            on("success").to "selectPurchaseOrderParameters"
        }

        selectPurchaseOrderParameters {
            on("confirm") { PurchaseOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Distributor distributor =distributorService.getDistributor(cmd.distributor)
                List<Product> products = []

                distributor.providers.each { provider ->
                    products << provider.products
                }

                [
                    distributor: distributor,
                    invoiceNumber: cmd.invoiceNumber,
                    paymentType: cmd.paymentType,
                    paymentDate: getPaymentDate(cmd.paymentType, distributor.daysToPay),
                    productList: products.flatten(),
                    products: products.flatten().unique() { a, b -> a.name <=> b.name }.sort { it.name },
                    items: [],
                    medicineOrders: [],
                    brandProductOrders: []
                ]
            }. to "items"

            on("cancel").to "done"
        }

        items {
            on("show").to "show"

            on("query") {
                String q = params?.q

                List<Product> result = flow.productList.findAll { it.name == q }

                Closure getSubmitName = {
                    Product instance = result[0]

                    if (instance instanceof Medicine) {
                        "addMedicineOrder"
                    } else if (instance instanceof BrandProduct) {
                        "addBrandProductOrder"
                    } else {
                        "addItem"
                    }
                }

                [result: result, q: q, submitName: getSubmitName()]
            }.to "items"

            on("addItem") { ItemComamnd cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Product product = Product.get(cmd.product)

                Item item = new Item(
                    product: product,
                    quantity: cmd.quantity,
                    purchasePrice: cmd.purchasePrice,
                    sellingPrice: cmd.sellingPrice
                )

                Item prod = flow.items.find { item.product == it.product }
                
                if (prod) {
                    item.quantity = cmd.quantity
                    item.purchasePrice = cmd.purchasePrice
                    item.sellingPrice = cmd.sellingPrice
                } else {
                    flow.items << item
                }
            }.to "items"

            on("addMedicineOrder") { MedicineOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                MedicineOrder medicineOrder = new MedicineOrder(
                    product: cmd.product,
                    quantity: cmd.quantity,
                    purchasePrice: cmd.purchasePrice,
                    sellingPrice: cmd.sellingPrice,
                    presentation: cmd.presentation,
                    measure: cmd.measure,
                    dueDate: cmd.dueDate
                )

                MedicineOrder item = flow.medicineOrders.find {
                    medicineOrder.product == it.product &&
                    medicineOrder.presentation == it.presentation &&
                    medicineOrder.measure == it.measure
                }

                if (item) {
                    item.dueDate = cmd.dueDate
                    item.quantity = cmd.quantity
                    item.purchasePrice = cmd.purchasePrice
                    item.sellingPrice = cmd.sellingPrice
                } else {
                    flow.medicineOrders << medicineOrder
                }
            }.to "items"

            on("addBrandProductOrder") { BrandProductOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                BrandProductOrder brandProductOrder = new BrandProductOrder(
                    product: cmd.product,
                    quantity: cmd.quantity,
                    purchasePrice: cmd.purchasePrice,
                    sellingPrice: cmd.sellingPrice,
                    brand: cmd.brand,
                    detail: cmd.detail
                )

                BrandProductOrder item = flow.brandProductOrders.find {
                    brandProductOrder.product== it.product &&
                    brandProductOrder.brand == it.brand &&
                    brandProductOrder.detail == it.detail
                }

                if (item) {
                    item.quantity = cmd.quantity
                    item.purchasePrice = cmd.purchasePrice
                    item.sellingPrice = cmd.sellingPrice
                } else {
                    flow.brandProductOrders << brandProductOrder
                }
            }.to "items"

            on("deleteItem") {
                Integer id = params.int("id")

                Item item = flow.items.find { id == it.product.id }
                    
                if (item) {
                    flow.items.remove(item)
                }
            }.to "items"

            on("deleteMedicineOrder") {
                Integer id = params.int("id")

                MedicineOrder medicineOrder = flow.medicineOrders.find { id == it.product.id }
                    
                if (medicineOrder) {
                    flow.medicineOrders.remove(medicineOrder)
                }
            }.to "items"

            on("deleteBrandProductOrder") {
                Integer id = params.int("id")

                BrandProductOrder brandProductOrder = flow.brandProductOrders.find { id == it.product.id }
                    
                if (brandProductOrder) {
                    flow.brandProductOrders.remove(brandProductOrder)
                }
            }.to "items"

            on("confirm") {
                PurchaseOrder purchaseOrder = new PurchaseOrder(
                    distributor: flow.distributor,
                    user: springSecurityService.currentUser,
                    invoiceNumber: flow.invoiceNumber,
                    paymentType: flow.paymentType,
                    paymentDate: flow.paymentDate
                )

                flow.items.each { item ->
                    purchaseOrder.addToItems(item)
                }

                flow.medicineOrders.each { medicineOrder ->
                    purchaseOrder.addToItems(medicineOrder)
                }

                flow.brandProductOrders.each { brandProductOrder ->
                    purchaseOrder.addToItems(brandProductOrder)
                }

                flash.message = "Proceso concluido correctamente"

                if (!purchaseOrder.save()) {
                    purchaseOrder.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }
            }.to "done"
        }

        show {
            on("confirm") { PurchaseOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[$error.field: $error.defaultMessage]"
                    }

                    return error()
                }

                Distributor distributor =distributorService.getDistributor(cmd.distributor)

                if (distributor != flow.distributor) {
                    flow.items = []
                    flow.medicineOrders = []
                    flow.brandProductOrders = []
                }

                flow.distributor = distributor
                flow.invoiceNumber = cmd.invoiceNumber
                flow.paymentDate = getPaymentDate(cmd.paymentType, distributor.daysToPay)
                flow.paymentType = cmd.paymentType

            }.to "show"

            on("goBack").to "items"
        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }

    private getPaymentDate(String paymentType, Integer daysToPay) {
        Date today = new Date()
        Date paymentDate = null

        if (paymentType == "credit") {
            paymentDate = today + daysToPay
        }

        paymentDate
    }
}

class PurchaseOrderCommand {
    Integer distributor
    String invoiceNumber
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}

class ItemComamnd {
    Long product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice

    static constraints = {
        importFrom Item
    }
}

class MedicineOrderCommand {
    Long product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice
    Integer presentation
    Integer measure
    Date dueDate

    static constraints = {
        importFrom MedicineOrder
    }
}

class BrandProductOrderCommand {
    Long product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice
    Long brand
    Long detail

    static constraints = {
        importFrom BrandProductOrder
    }
}
