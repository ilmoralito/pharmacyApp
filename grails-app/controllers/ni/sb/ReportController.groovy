package ni.sb

import grails.plugin.springsecurity.annotation.Secured
import org.hibernate.transform.AliasToEntityMapResultTransformer
import static java.util.Calendar.*

@Secured(["ROLE_ADMIN"])
class ReportController {
    def springSecurityService
    def saleService
    def helperService
    def saleDetailService
    def expenseService

    static defaultAction = "sales"
    static allowedMethods = [
        sales: ["GET", "POST"],
        stock: ["GET", "POST"],
        clients: ["GET", "POST"],
        detail: "GET",
        expenses: "GET",
        expensesDetail: ["GET","POST"],
        employees: ["GET", "POST"]
    ]

    private static final MONTHS = [
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    ]

    def sales(String field) {
        Closure getFromAndToDates = {
            if (request.post) {
                [
                    params.date("from", "yyyy-MM-dd") ?: new Date(),
                    params.date("to", "yyyy-MM-dd") ?: new Date()
                ]
            } else {
                helperService.getDates(field)
            }
        }

        def(Date from, Date to) = getFromAndToDates()
        List<SaleDetail> saleDetails = saleDetailService.getSaleDetails(from, to)
        List<CashSale> cashSales = saleService.getCashSales(from,to)
        List<CashSale> canceledCashSales = saleService.getCashSales(from, to, true)

        [
            saleDetails: saleDetailService.getSaleDetailSummary(saleDetails),
            balance: saleService.getBalanceSummary(cashSales),
            balanceCanceledSales: saleService.getBalanceSummary(canceledCashSales),
            expenseBalance: expenseService.getExpensesBalanceSummary(from, to)
        ]
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
                            name: b.getFullName(),
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
                            name: b.getFullName(),
                            quantity: b.quantity
                        ]
                    }
                ]
            }

            [result: brandProducts]
        }
    }

    def clients(String field) {
        Closure getFromAndToDates = {
            if (request.post) {
                [
                    params.date("from", "yyyy-MM-dd") ?: new Date(),
                    params.date("to", "yyyy-MM-dd") ?: new Date()
                ]
            } else {
                helperService.getDates(field)
            }
        }

        def(Date from, Date to) = getFromAndToDates()

        List<Sale> sales = saleService.getCashSales(from,to)
        List<Map<String, String>> clients = saleService.getSummary(sales)

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

    def expenses() {
        List data = Expense.list().groupBy { it.dateCreated[YEAR] } { it.dateCreated[MONTH] }.collect { a ->
            [
                year: a.key,
                months: (0..11).collect { m ->
                    [
                        name: MONTHS[m],
                        total: a.value[m]?.quantity?.sum() ?: 0,
                        quantity: a.value[m]?.size() ?: 0
                    ]
                }
            ]
        }.sort { -it.year }

        [data: data]
    }

    def expensesDetail(Integer y, Integer m) {
        def(Date from, Date to) = helperService.getDates("month")
        List<Expense> expenses = Expense.where { year(dateCreated) == y && month(dateCreated) == m }.list()
        Map data = expenses.groupBy { it.dateCreated[DATE] }
        List result = (from..to).collect { d ->
            [
                day: d[DATE],
                expenses: data[d[DATE]].collect { e->
                    [
                        fullName: e?.user?.fullName,
                        description: e?.description,
                        quantity: e?.quantity
                    ]
                }.sort { it.fullName }
            ]
        }

        List resume = expenses.groupBy { it.user.fullName }.collect { a ->
            [
                fullName: a.key,
                quantity: a.value.quantity.sum()
            ]
        }.sort { -it.quantity }

        [
            result: result,
            resume: resume,
            month: MONTHS[m],
            year: y
        ]
    }

    def employees() {

    }
}
