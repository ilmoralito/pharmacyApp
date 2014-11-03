package ni.sb

class SaleToClient extends Sale {
	Client client
  String typeOfPurchase
  String status

  static constraints = {
    client nullable:false, validator:{ client ->
    	if (!client.status) {
    		"saleToClient.client.invalidClient"
    	}
    }
    typeOfPurchase inList:["Contado", "Credito"]
    status inList:["Pendiente", "Cancelado"], maxSize:255
  }

  def beforeInsert() {
    if (typeOfPurchase == "Contado") {
      status = "Cancelado"
    } else {
      status = "Pendiente"
    }
  }

  static mapping = {
  	version false
  }

  String toString() { client }
}
