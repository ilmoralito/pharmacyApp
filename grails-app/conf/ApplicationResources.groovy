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

    employees {
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

    pay {
        resource url: "js/pay.js"
    }
}