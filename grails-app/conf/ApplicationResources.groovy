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
}
