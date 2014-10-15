modules = {
  app {
    resource url:"css/main.css"
  }

  createPurchaseOrder {
  	dependsOn "app"
    dependsOn "calculateSellingPrice"
    resource url:"js/application.js"
  }

  brandProduct {
    dependsOn "app"
    resource url:"js/brandProduct.js"
  }

  saleFilter {
  	dependsOn "app"
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

  calculateSellingPrice {
    resource url:"js/calculateSellingPrice.js"
  }
}
