modules = {
    app {
        resource url: "css/main.css"
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

    addTelefone {
        dependsOn "app"
        resource url: "js/addTelefone.js"
    }

    pay {
        resource url: "js/pay.js"
    }
}