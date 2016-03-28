package ni.sb

import groovy.xml.MarkupBuilder

class AppTagLib {
    def saleService

    static defaultEncodeAs = "html"
    static encodeAsForTags = [
        getSaleBalance: "raw",
        presentations: "raw",
        productBackLick: "raw",
        measures: "raw"
    ]

    static namespace = "pharmacyApp"

    def presentations = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Presentation> presentations = Presentation.list()
        Map checkboxParams = [type: "checkbox", name: "presentations"]

        builder.div {
            presentations.each { presentation ->
                div(class: "checkbox") {
                    checkboxParams.value = presentation.id

                    if (attrs.product?.presentations?.contains(presentation)) {
                        checkboxParams.checked = true
                    } else {
                        checkboxParams.remove "checked"
                    }

                    label {
                        input(checkboxParams)
                    }

                    span {
                        mkp.yield(presentation.name)
                    }
                }

                ul(class: "hide") {
                    presentation.measures.each { measure ->
                        li {
                            mkp.yield measure
                        }
                    }
                }
            }
        }
    }

    def productBackLick = { attrs ->
        String uri = ""

        if (!(attrs.product instanceof Medicine || attrs.product instanceof BrandProduct)) {
            uri = "productList"
        } else if (attrs.product instanceof Medicine) {
            uri = "medicineList"
        } else {
            uri = "brandList"
        }

        out << g.link(
            action: uri,
            params: [providerId: attrs.product.provider.id],
            fragment: attrs.product.id
        ) {
            "Regresar"
        }
    }

    def measures = { attrs ->
        List<Measure> measures = Measure.list()
        MarkupBuilder builder = new MarkupBuilder(out)
        Map checkboxParams = [type: "checkbox", name: "measures"]

        builder.div {
            div(class: "form-group") {
                input(
                    type: "search",
                    id: "search",
                    class: "form-control",
                    placeholder: "Filtrar"
                )
            }

            measures.each { measure ->
                checkboxParams.value = measure.id

                if (attrs.presentation) {
                    if (attrs.presentation.measures.contains(measure)) {
                        checkboxParams.checked = true
                    } else {
                        checkboxParams.remove "checked"
                    }
                }

                div(class: "checkbox") {
                    label {
                        input(checkboxParams)
                    }

                    span {
                        mkp.yield measure
                    }
                }
            }
        }
    }

    def purchaseOrderStatus = { attrs ->
        if (attrs.status) {
            out << "Pagado"
        } else {
            out << "Pendiente"
        }
    }

    def getSaleBalance = { attrs ->
        def balance = saleService.calcSaleBalance(attrs.medicinesToSale, attrs.productsToSale, attrs.brandsToSale)

        out << render(template:"createSaleToClient/balanceTemplate", model:[balance:balance])
    }

    def calcReceiptNumber = { attrs ->
        def payInstance = Pay.list(sort: "receiptNumber", order: "asd"), lastPay, receiptNumber
        if (payInstance.size() > 0) {
            lastPay = payInstance.last()
            receiptNumber = lastPay.receiptNumber + 1
        }else{
            receiptNumber = 1
        }

        out << receiptNumber
    }
}
