package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class PurchaseOrderController {
    def springSecurityService
    def distributorService
    def itemService
    def purchaseOrderService

    static defaultAction = "list"
    static allowedMethods = [
        list: ["GET", "POST"],
        show: "GET",
        update: "POST",
        create: "GET",
        delete: "GET"
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
                PurchaseOrder.list()
            }
        }

        [purchaseOrders: purchaseOrders()]
    }

    def show(Long id) {
        PurchaseOrder po = PurchaseOrder.get(id)

        if (!po) {
            response.sendError 404
        }

        println po.items.size()

        List items = po.items.findAll {!(it instanceof MedicineOrder) && !(it instanceof BrandProductOrder)}
        List medicineOrders = po.items.findAll {it instanceof MedicineOrder}
        List brandProductOrders = po.items.findAll {it instanceof BrandProductOrder}

        [
            purchaseOrder: po,
            items: items,
            medicineOrders: medicineOrders,
            brandProductOrders: brandProductOrders,
            soledItems: itemService.getSoledItems(po.items),
            purchaseOrderDetail: createPurchaseOrderDetail(po),
            itemsResume: createPurchaseOrderResume(items, "Productos"),
            medicinesResume: createPurchaseOrderResume(medicineOrders, "Medicinas"),
            brandProductOrdersResume: createPurchaseOrderResume(brandProductOrders, "Marcas")
        ]
    }

    def update(Long id) {
        PurchaseOrder purchaseOrder = PurchaseOrder.get(id)

        if (!purchaseOrder) {
            response.sendError 404
        }

        purchaseOrder.properties["invoiceNumber", "paymentType", "paymentStatus"] = params

        if (!purchaseOrder.save()) {
            purchaseOrder.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = purchaseOrder
        }

        flash.message = purchaseOrder.hasErrors() ? "A ocurrido un error" : "Actualizado correctamente"
        redirect action: "show", params: [id: id, tab: params.tab]
    }

    def create() {
        redirect action: "createPurchaseOrder"
    }

    def delete(Long id) {
        PurchaseOrder po = PurchaseOrder.get(id)

        if (!po) {
            response.sendError 404
        }

        List<Item> affectedItems = itemService.getSoledItems(po.items)

        if (!affectedItems) {
            po.delete(flush: true)
        }

        flash.message = affectedItems ? "No permitido eliminar pedido" : "Pedido eliminado"
        redirect action: "list"
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

                List<Product> products = []

                cmd.distributor.providers.each { provider ->
                    products << provider.products
                }

                [
                    distributor: cmd.distributor,
                    invoiceNumber: cmd.invoiceNumber,
                    paymentType: cmd.paymentType,
                    paymentDate: cmd.paymentType == "credit" ? new Date() + cmd.distributor.daysToPay : null,
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
            on("updatePurchaseOrder") { PurchaseOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    return error()
                }

                if (cmd.distributor != flow.distributor) {
                    List<Product> tempProducts = []

                    // Remove added items
                    flow.items = []
                    flow.medicineOrders = []
                    flow.brandProductOrders = []

                    // Remove products
                    flow.products = []

                    // Get current distributor products
                    cmd.distributor.providers.each { provider ->
                        tempProducts << provider.products
                    }

                    // Add current distributor products
                    flow.products = tempProducts.flatten().unique() { a, b -> a.name <=> b.name }.sort { it.name }
                }

                flow.distributor = cmd.distributor
                flow.invoiceNumber = cmd.invoiceNumber
                flow.paymentDate = cmd.paymentType == "credit" ? new Date() + cmd.distributor.daysToPay : null
                flow.paymentType = cmd.paymentType
            }.to "items"

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
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    return error()
                }

                Item item = new Item(
                    product: cmd.product,
                    quantity: cmd.quantity,
                    fixedQuantity: cmd.quantity,
                    purchasePrice: cmd.purchasePrice,
                    sellingPrice: cmd.sellingPrice
                )

                Item prod = flow.items.find { item.product == it.product }

                if (prod) {
                    prod.quantity = cmd.quantity
                    prod.purchasePrice = cmd.purchasePrice
                    prod.sellingPrice = cmd.sellingPrice
                } else {
                    flow.items << item
                }

                flow.productTab = ""
            }.to "items"

            on("addMedicineOrder") { MedicineOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    return error()
                }

                MedicineOrder medicineOrder = new MedicineOrder(
                    product: cmd.product,
                    quantity: cmd.quantity,
                    fixedQuantity: cmd.quantity,
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

                flow.productTab = "medicineTab"
            }.to "items"

            on("addBrandProductOrder") { BrandProductOrderCommand cmd ->
                if (cmd.hasErrors()) {
                    cmd.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    return error()
                }

                BrandProductOrder brandProductOrder = new BrandProductOrder(
                    product: cmd.product,
                    quantity: cmd.quantity,
                    fixedQuantity: cmd.quantity,
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

                flow.productTab = "brandProductTab"
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
                    paymentStatus: flow.paymentType == "credit" ? "pending" : "paid",
                    paymentDate: flow.paymentDate
                )

                flow.items.each { item ->
                    purchaseOrder.addToItems(item)

                    def query = Item.where {
                        product == item.product
                    }

                    Integer total = query.updateAll(sellingPrice: item.sellingPrice)
                }

                flow.medicineOrders.each { medicineOrder ->
                    purchaseOrder.addToItems(medicineOrder)

                    def query = MedicineOrder.where {
                        product == medicineOrder.product &&
                        presentation == medicineOrder.presentation &&
                        measure == medicineOrder.measure
                    }

                    Integer total = query.updateAll(sellingPrice: medicineOrder.sellingPrice)
                }

                flow.brandProductOrders.each { brandProductOrder ->
                    purchaseOrder.addToItems(brandProductOrder)

                    def query = BrandProductOrder.where {
                        product == brandProductOrder.product &&
                        brand == brandProductOrder.brand &&
                        detail == brandProductOrder.detail
                    }

                    Integer total = query.updateAll(sellingPrice: brandProductOrder.sellingPrice)
                }

                if (!purchaseOrder.save()) {
                    purchaseOrder.errors.allErrors.each { error ->
                        log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                    }

                    flash.message = "A ocurrido un error. Log notificado"
                    return error()
                }

                flash.message = "Proceso concluido correctamente"
            }.to "done"

            on("changeItemsTab") {
                flow.tab = params?.tab
            }.to "items"

            on("changeProductListTab") {
                flow.productTab = params.productTab
            }.to "items"
        }

        done {
            redirect controller: "purchaseOrder", action: "list"
        }
    }

    private PurchaseOrderResume createPurchaseOrderResume(List<Item> items, String label) {
        new PurchaseOrderResume(
            label: label,
            products: items.size(),
            totalPurchasePrice: purchaseOrderService.calculateTotal(items, "purchasePrice"),
            totalSellingPrice: purchaseOrderService.calculateTotal(items, "sellingPrice"),
            totalProfits: purchaseOrderService.getProfits(items)
        )
    }

    private PurchaseOrderDetail createPurchaseOrderDetail(PurchaseOrder po) {
        new PurchaseOrderDetail(
            distributor: po.distributor.name,
            invoiceNumber: po.invoiceNumber,
            createdBy: po.user.fullName,
            dateCreated: po.dateCreated.format("yyyy-MM-dd"),
            paymentType: po.paymentType,
            paymentDate: po?.paymentDate?.format("yyyy-MM-dd"),
            paymentStatus: po.paymentStatus
        )
    }
}

class PurchaseOrderDetail {
    String distributor
    String invoiceNumber
    String createdBy
    String dateCreated
    String paymentType
    String paymentDate
    String paymentStatus
}

class PurchaseOrderResume {
    String label
    Integer products
    BigDecimal totalPurchasePrice
    BigDecimal totalSellingPrice
    BigDecimal totalProfits
}

class PurchaseOrderCommand {
    Distributor distributor
    String invoiceNumber
    String paymentType

    static constraints = {
        importFrom PurchaseOrder
    }
}

class ItemComamnd {
    Product product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice

    static constraints = {
        importFrom Item
    }
}

class MedicineOrderCommand {
    Product product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice
    Presentation presentation
    Measure measure
    Date dueDate

    static constraints = {
        importFrom MedicineOrder
    }
}

class BrandProductOrderCommand {
    Product product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice
    Brand brand
    Detail detail

    static constraints = {
        importFrom BrandProductOrder
    }
}
