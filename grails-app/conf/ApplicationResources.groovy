modules = {
    app {
        resource url:"css/main.css"
    }

    // EMPLOYEE
    employees {
        dependsOn "app"
        resource url: "js/filter-table.js"
    }

    addTelefone {
        dependsOn "app"
        resource url:"js/addTelefone.js"
    }

    createAndUpdatePurchaseOrder {
        dependsOn "app"
        resource url:"js/createAndUpdatePurchaseOrder.js"
    }

    adminMedicine {
        dependsOn "app"
        dependsOn "calculateSellingPrice"
        resource url:"js/application.js"
    }

    brandProduct {
        dependsOn "app"
        dependsOn "calculateSellingPrice"
        resource url:"js/brandProduct.js"
    }

    calculateSellingPrice {
        resource url:"js/calculateSellingPrice.js"
    }

    saleFilter {
        dependsOn "app"
        resource url:"css/bootstrap-multiselect.css"
        resource url:"js/bootstrap-multiselect.js"
        resource url:"js/saleFilter.js"
    }

    chosenLib {
        dependsOn "app"
        dependsOn "chosen"
        resource url:"js/chosenLib.js"
    }

    chosen { 
        resource url:"js/chosen.js"
        resource url:"css/chosen.css"
    }

    productMedicine {
        dependsOn "app"
        resource url:"js/productMedicine.js"
    }

    modal {
        resource url:"js/modal.js"
    }

    saleMedicine {
        resource url:"js/saleMedicine.js"
    }

    adding {
        resource url:"js/adding.js"
    }

    pay {
        resource url:"js/pay.js"
    }

    filterStock {
        resource url:"js/filterStock.js"
    }

    reports {
        resource url:"js/reports.js"
    }

    addProduct {
        resource url:"js/addProduct.js"
    }
}