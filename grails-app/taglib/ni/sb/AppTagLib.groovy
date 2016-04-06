package ni.sb

import groovy.xml.MarkupBuilder

class AppTagLib {
    def saleService
    def distributorService

    static defaultEncodeAs = "html"
    static encodeAsForTags = [
        getSaleBalance: "raw",
        presentations: "raw",
        productBackLick: "raw",
        measures: "raw",
        users: "raw",
        details: "raw",
        brands: "raw",
        dealers: "raw",
        paymentTypeBox: "raw",
        fromTo: "raw",
        paymentStatus: "raw",
        combo: "raw"
    ]

    static namespace = "pharmacyApp"

    def presentations = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Presentation> presentations = Presentation.list()
        Map checkboxParams = [type: "checkbox", name: "presentations"]

        builder.div {
            p {
                mkp.yield "Presentaciones"
            }

            div(class: "form-group") {
                input(type: "search", id: "search", class: "form-control", placeholder: "Filtrar")
            }

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

                    span(class: "presentations", style: "cursor: pointer;") {
                        mkp.yield(presentation.name)
                    }

                    ol(class: "hide") {
                        presentation.measures.each { measure ->
                            li {
                                mkp.yield measure
                            }
                        }
                    }
                }
            }
        }
    }

    def productBackLick = { attrs ->
        def product =  attrs.product
        Closure uri = {
            if (!(product instanceof Medicine || product instanceof BrandProduct)) {
                "productList"
            } else if (product instanceof Medicine) {
                "medicineList"
            } else {
                "brandProductList"
            }
        }

        out << g.link(
            action: uri(),
            params: [providerId: product.provider.id],
            fragment: product.id
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

                    span(id: measure.id) {
                        mkp.yield measure
                    }
                }
            }
        }
    }

    def users = { attrs ->
        List<Long> userList = attrs.userList*.toLong()
        List<User> users = User.list()
        MarkupBuilder builder = new MarkupBuilder(out)
        Map params = [type: "checkbox", name: "users"]

        builder.div {
            p "Usuarios"

            users.each { user ->
                params.value = user.id

                if (userList) {
                    if (userList.contains(user.id)) {
                        params.checked = true
                    } else {
                        params.remove "checked"
                    }
                }

                div(class: "checkbox") {
                    label {
                        input(params)
                    }

                    span {
                        mkp.yield user.fullName
                    }
                }
            }
        }
    }

    def details = { attrs ->
        List<Detail> brandDetails = attrs?.brand?.details
        List<Detail> details = Detail.list()
        MarkupBuilder builder = new MarkupBuilder(out)
        Map<String, String> checkboxParams = [type: "checkbox", name: "details"]

        builder.div {
            p {
                mkp.yield "Detalles"
            }

            div(class: "form-group") {
                input(type: "search", id: "search", class: "form-control", placeholder: "Filtrar")
            }

            details.each { detail ->
                checkboxParams.value = detail.id

                if (brandDetails) {
                    if (brandDetails.contains(detail)) {
                        checkboxParams.checked = true
                    } else {
                        checkboxParams.remove("checked")
                    }
                }

                div(class: "checkbox") {
                    label {
                        input(checkboxParams)
                    }

                    span(id: detail.id) {
                        mkp.yield detail.name
                    }
                }
            }
        }
    }

    def brands = { attrs ->
        List<Brand> productBrands = attrs?.product?.brands
        List<Brand> brands = Brand.list()
        MarkupBuilder builder = new MarkupBuilder(out)
        Map<String, String> checkboxParams = [type: "checkbox", name: "brands"]

        builder.div {
            p {
                mkp.yield "Marcas"
            }

            div(class: "form-group") {
                input(type: "search", id: "search", class: "form-control", placeholder: "Filtrar")
            }

            brands.each { brand ->
                checkboxParams.value = brand.id

                if (productBrands) {
                    if (productBrands.contains(brand)) {
                        checkboxParams.checked = true
                    } else {
                        checkboxParams.remove("checked")
                    }
                }

                div(class: "checkbox") {
                    label {
                        input(checkboxParams)
                    }

                    span(id: brand.id) {
                        mkp.yield brand.name
                    }
                }
            }
        }
    }

    def dealers = { attrs ->
        String type = attrs.type
        List<Integer> dealerList = attrs.list("dealerList")
        List<Distributor> dealers = distributorService.getValidDistributors()
        MarkupBuilder builder = new MarkupBuilder(out)
        Map<String, String> params = [type: attrs.type, name: "distributor"]

        builder.div {
            p "Distribuidores"

            dealers.each { dealer ->
                params.value = dealer.id

                if (dealerList?.contains(dealer.id)) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                div(class: attrs.type) {
                    label {
                        input(params)
                        mkp.yield dealer.name
                    }
                }

                ol(class: "hide") {
                    dealer.providers.each { provider ->
                        li {
                            mkp.yield provider.name
                        }
                    }
                }
            }
        }
    }

    def paymentTypeBox = { attrs ->
        String type = attrs.type
        String pt = attrs.paymentType
        MarkupBuilder builder = new MarkupBuilder(out)
        Map<String, String> paymentTypes = [credit: "Credito", cash: "Contado"]
        Map<String, String> params = [type: type, name: "paymentType"]

        builder.div {
            p "Tipos de pago"

            paymentTypes.each { paymentType ->
                params.value = paymentType.key

                if (paymentType.key == pt) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                div(class: type) {
                    label {
                        input(params)
                        mkp.yield paymentType.value
                    }
                }
            }
        }
    }

    def fromTo = {
        MarkupBuilder builder = new MarkupBuilder(out)

        builder.div {
            p "Fechas"

            div(class: "form-group") {
                input(name: "from", class: "form-control", placeholder: "Desde")
            }

            div(class: "form-group") {
                input(name: "to", class: "form-control", placeholder: "Hasta")
            }
        }
    }

    def paymentStatus = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        Map<String, String> params = [type: "checkbox", name: "paymentStatus"]

        builder {
            p "Estado de pago"

            div(class: "checkbox") {
                params.value = "pending"

                label {
                    input(params)

                    mkp.yield "Pendiente"
                }
            }

            div(class: "checkbox") {
                params.value = "paid"

                label {
                    input(params)

                    mkp.yield "Cancelado"
                }
            }
        }
    }

    def combo = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        String name = attrs.name
        String data = attrs.data
        List<Product> from = attrs.from
        Map<String, String> params = [:]

        builder.div(class: "form-group") {
            delegate.select(name: name, class: "form-control") {
                from.each { o ->
                    params.value = o.id

                    if (data != null) {
                        params["data-nami"] = o[data]*.name
                    }

                    option(params) {
                        mkp.yield o.name
                    }
                }
            }
        }
    }

    def paymentType = { attrs ->
        if (attrs.type == "cash") {
            out << "Contado"
        } else {
            out << "Credito"
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
