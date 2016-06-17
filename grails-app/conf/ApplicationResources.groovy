modules = {
    app {
        resource url: "css/main.css"
    }

    sale {
        dependsOn "app"
        dependsOn "select2"
        dependsOn "autosize"
        dependsOn "mustache"
        resource url: "js/sale.js"
    }

    purchaseOrder {
        dependsOn "app"
        dependsOn "select2"
        dependsOn "bootstrap3datepicker"
        resource url: "js/purchase-order.js"
    }

    filterTable {
        dependsOn "app"
        resource url: "js/filter-table.js"
    }

    filter {
        dependsOn "app"
        resource url: "js/filter.js"
    }

    updateName {
        dependsOn "filter"
        resource url: "js/updateName.js"
    }

    bootstrap3datepicker {
        resource url: "/components/moment/min/moment.min.js"
        resource url: "/components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"
        resource url: "/components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"
    }

    select2 {
        resource url: "/components/select2/dist/js/select2.min.js"
        resource url: "/components/select2/dist/css/select2.min.css"
        resource url: "/components/select2-bootstrap-theme/dist/select2-bootstrap.min.css"
    }

    mustache {
        resource url: "/components/mustache.js/mustache.min.js"
    }

    sweetalert {
        resource url: "/components/sweetalert/dist/sweetalert.css"
        resource url: "/components/sweetalert/dist/sweetalert.min.js"
    }

    autosize {
        resource url: "/components/autosize/dist/autosize.min.js"
    }

    fromToDatepicker {
        resource url: "/js/datepicker.js"
    }

    alertBox {
        dependsOn "sweetalert"
        resource url: "/js/alert-box.js"
    }
}