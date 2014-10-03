modules = {
  app {
    resource url:"css/main.css"
  }

  createPurchaseOrder {
  	dependsOn "app"
  	resource url:"js/application.js"
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

  productMedicine
  {
    resource url:"js/productMedicine.js"
  }
}
