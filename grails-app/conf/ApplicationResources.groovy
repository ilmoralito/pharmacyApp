modules = {
    app {
        resource url: "css/main.css"
    }

    sale {
        dependsOn "app"
        resource url: "js/sale.js"
    }

    purchaseOrder {
        dependsOn "app"
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

    fromToDatepicker {
        resource url: "/js/datepicker.js"
    }
}