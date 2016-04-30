package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.hibernate.transform.AliasToEntityMapResultTransformer

@Secured(["ROLE_ADMIN"])
class ReportController {
    def springSecurityService
    def saleService

    static defaultAction = "sales"
    static allowedMethods = [
        sales: ["GET", "POST"],
        stock: ["GET", "POST"],
        clients: ["GET", "POST"],
        detail: "GET",
        employees: ["GET", "POST"]
    ]

    def sales() {
        if (request.post) {
            Date from = params.date("from", "yyyy-MM-dd") ?: new Date()
            Date to = params.date("to", "yyyy-MM-dd") ?: new Date()

            List data = SaleDetail.fromTo(from, to).list()
            BigDecimal balance = Sale.fromTo(from, to).list().balance.sum()
            BigDecimal expenseBalance = Expense.fromTo(from, to).list().quantity.sum()
            List saleDetails = data.groupBy { it.item }.collect { a ->
                [
                    item: a.key,
                    quantity: a.value.quantity.sum()
                ]
            }.sort { -it.quantity }

            return [
                saleDetails: saleDetails,
                balance: balance ?: 0.0,
                expenseBalance: expenseBalance ?: 0.0
            ]
        }
    }

    def stock(String flag) {
        if (!flag) {
            List<Item> items = Item.list().findAll { item ->
                !(item instanceof MedicineOrder) && !(item instanceof BrandProductOrder)
            }
            
            List data = items.groupBy { it.product.provider }.collect { a ->
                [
                    labName: a.key,
                    labProducts: a.value.collect { b ->
                        [
                            name: b,
                            quantity: b.quantity
                        ]
                    }
                ]
            }

            [result: data]
        } else if (flag == "medicines") {
            List medicines = MedicineOrder.list().groupBy { it.product.provider }.collect { a ->
                [
                    labName: a.key,
                    labProducts: a.value.collect { b ->
                        [
                            name: b,
                            presentation: b.presentation,
                            measure: b.measure,
                            quantity: b.quantity
                        ]
                    }
                ]
            }

            [result: medicines]
        } else if (flag == "brandProducts") {
            List brandProducts = BrandProductOrder.list().groupBy { it.product.provider }.collect { a ->
                [
                    labName: a.key,
                    labProducts: a.value.collect { b ->
                        [
                            name: b,
                            brand: b.brand,
                            detail: b.detail,
                            quantity: b.quantity
                        ]
                    }
                ]
            }

            [result: brandProducts]
        }
    }

    def clients() {
        Closure getSales = {
            if (request.post) {
                Date from = params.date("from", "yyyy-MM-dd") ?: new Date()
                Date to = params.date("to", "yyyy-MM-dd") ?: new Date()

                log.info "Custom query"
                log.info "Quering from ${from.format('yyyy-MM-dd')} to ${to.format('yyyy-MM-dd')}"
                Sale.fromTo(from, to).list()
            } else {
                saleService.getSalesFromField(params?.field)
            }
        }

        List<Map<String, String>> clients = saleService.getSummary(getSales())

        [clients: clients]
    }

    def detail(Long id) {
        List<SaleDetail> saleDetails = SaleDetail.createCriteria().list {
            sale {
                client {
                    eq "id", id
                }
            }

            projections {
                groupProperty "item", "item"
                count "item", "quantity"
            }

            order("quantity", "desc")
            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }

        [result: saleDetails]
    }

    def employees() {

    }
}