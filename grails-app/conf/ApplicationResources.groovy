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

    adminMedicine {
        dependsOn "app"
        dependsOn "calculateSellingPrice"
        resource url: "js/application.js"
    }

    brandProduct {
        dependsOn "app"
        dependsOn "calculateSellingPrice"
        resource url: "js/brandProduct.js"
    }

    calculateSellingPrice {
        resource url: "js/calculateSellingPrice.js"
    }

    saleFilter {
        dependsOn "app"
        resource url: "css/bootstrap-multiselect.css"
        resource url: "js/bootstrap-multiselect.js"
        resource url: "js/saleFilter.js"
    }

    modal {
        resource url: "js/modal.js"
    }

    saleMedicine {
        resource url: "js/saleMedicine.js"
    }

    adding {
        resource url: "js/adding.js"
    }

    pay {
        resource url: "js/pay.js"
    }

    reports {
        resource url: "js/reports.js"
    }

    addProduct {
        resource url: "js/addProduct.js"
    }
}