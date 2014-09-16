package ni.sb

class SaleToClient extends Sale {
	Client client

  static constraints = {
    client nullable:false, validator:{ client ->
    	if (!client.status) {
    		"saleToClient.client.invalidClient"
    	}
    }
  }

  static mapping = {
  	version false
  }

  String toString() { client }
}
