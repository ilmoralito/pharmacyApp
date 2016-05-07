package ni.sb

import groovy.xml.MarkupBuilder
import groovy.json.JsonOutput

class AppTagLib {
    def distributorService

    static defaultEncodeAs = "html"
    static encodeAsForTags = [
        presentations: "raw",
        productBackLick: "raw",
        measures: "raw",
        users: "raw",
        details: "raw",
        brands: "raw",
        dealers: "raw",
        paymentTypeBox: "raw",
        fromTo: "raw",
        paymentStatusBox: "raw",
        combo: "raw",
        clientsDataList: "raw",
        providers: "raw"
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
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Long> userList = attrs.userList*.toLong()
        List<User> users = User.list()
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
        MarkupBuilder builder = new MarkupBuilder(out)
        String type = attrs.type
        List<Integer> dealerList = attrs.list("dealerList")*.toLong()
        Boolean showMessage = attrs.boolean("showMessage")
        List<Distributor> dealers = distributorService.getValidDistributors()
        Map<String, String> params = [type: type, name: attrs?.name ?: "distributor"]

        builder.div {
            p {
                mkp.yield "Distribuidores"
                if (showMessage) {
                    div {
                        small "Si cambia de distribuidor se eliminaran los articulos agregados"
                    }
                }
            }

            dealers.each { dealer ->
                params.value = dealer.id

                if (dealerList?.contains(dealer.id)) {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                div(class: type) {
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
        MarkupBuilder builder = new MarkupBuilder(out)
        String type = attrs.type
        def pt = attrs.paymentType
        Map<String, String> paymentTypes = [credit: "Credito", cash: "Contado"]
        Map<String, String> params = [type: type, name: "paymentType"]

        builder.div {
            label "Tipos de pago"

            paymentTypes.eachWithIndex { paymentType, idx ->
                params.value = paymentType.key

                if (pt instanceof String ? paymentType.key == pt : paymentType.key in pt) {
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

    def fromTo = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Map> params = [
            [
                id: "from",
                name: "from",
                value: attrs?.from,
                class: "form-control",
                placeholder: "Desde"
            ],[
                id: "to",
                name: "to",
                value: attrs?.to,
                class: "form-control",
                placeholder: "Hasta"
            ]
        ]

        builder.div {
            params.each { m ->
                div(class: "form-group") {
                    input(m)
                }
            }
        }
    }

    def paymentStatusBox = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        def status = attrs.paymentStatusList
        String type = attrs.type
        Map<String, String> params = [type: type, name: "paymentStatus"]

        builder {
            label "Estado de pago"

            div(class: type, style: "margin-top: 5px;") {
                params.value = "pending"

                if (status instanceof List ? "pending" in status : status == "pending") {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

                label {
                    input(params)
                    mkp.yield "Pendiente"
                }
            }

            div(class: type) {
                params.value = "paid"

                if (status instanceof List ? "paid" in status : status == "paid") {
                    params.checked = true
                } else {
                    params.remove("checked")
                }

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
                        params["data-info"] = JsonOutput.toJson(o[data].collect { [id: it.id, name: it.name] })
                    }

                    option(params) {
                        mkp.yield o.name
                    }
                }
            }
        }
    }

    def clientsDataList = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Client> clients = Client.findAllByEnabled(true)
        Integer clientID = attrs?.clientID

        out << g.select(
            name:"client.id",
            noSelection: ['': '-Selecciona cliente-'],
            from: clients,
            optionKey: "id",
            optionValue: "fullName",
            value: clientID,
            class: "form-control"
        )
    }

    def providers = { attrs ->
        MarkupBuilder builder = new MarkupBuilder(out)
        List<Provider> providerList = attrs.providerList
        List<Provider> providers = Provider.where {
            enabled == true && products.size() > 0
        }.list()
        Map<String, String> params = [type: "checkbox", name: "providers"]

        if (providers) {
            builder.div {
                providers.each { provider ->
                    params.value = provider.id

                    if (provider in providerList) {
                        params.checked = true
                    } else {
                        params.remove("checked")
                    }

                    div(class: "checkbox") {
                        label {
                            input(params) { mkp.yield provider.name }
                        }
                    }
                }
            }
        } else {
            out << "<p>"
            out << g.link(controller: "provider", action: "list") { "Agregar laboratorios" }
            out << "</p>"
        }
    }

    def field = { attrs ->
        String field = attrs.field
        String result = ""

        switch(field) {
            case "month":
                result = "Mes"
            break
            case "week":
                result = "Semana"
            break
            case "year":
                result = "Año"
            break
            case "custom":
                result = "Personalizado"
            break
        }

        out << result
    }

    // TODO: Implement a groovier solution
    def calcTotal = { attrs ->
        List items =attrs.items
        String property = attrs.property
        BigDecimal total = 0.0

        items.each { item ->
            BigDecimal result = item[property] * item.quantity

            total += result
        }

        out << total
    }

    def paymentType = { attrs ->
        if (attrs.type == "cash") {
            out << "Contado"
        } else {
            out << "Credito"
        }
    }

    def purchaseOrderStatus = { attrs ->
        if (attrs.status == "paid") {
            out << "Pagado"
        } else {
            out << "Pendiente"
        }
    }

    def paymentDate = { attrs ->
        MarkupBuilder mb = new MarkupBuilder(out)
        Date today = new Date()
        Date paymentDate = attrs.paymentDate
        Integer difference = paymentDate - today

        mb.div {
            if (difference <= 15) {
                span(class: "label label-info") {
                    mkp.yield "A $difference dias"
                }
            } else if (difference <= 10) {
                span(class: "label label-danger") {
                    mkp.yield "A $difference dias"
                }
            }
        }
    }
}
