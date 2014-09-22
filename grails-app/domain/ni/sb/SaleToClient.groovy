package ni.sb

class SaleToClient extends Sale {
	Client client
  String typeOfPurchase

  static constraints = {
    client nullable:false, validator:{ client ->
    	if (!client.status) {
    		"saleToClient.client.invalidClient"
    	}
    }
    typeOfPurchase inList:["Contado", "Credito"]
  }

  static mapping = {
  	version false
  }

  String toString() { client }
}
