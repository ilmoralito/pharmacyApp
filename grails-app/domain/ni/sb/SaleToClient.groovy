package ni.sb

class SaleToClient extends Sale {
	Client client
  String typeOfPurchase
  String status
  List pays

  static constraints = {
    client nullable:false, validator:{ client ->
      if (!client.status) {
        "saleToClient.client.invalidClient"
      }
    }
    typeOfPurchase inList:["Contado", "Credito"]
    status inList:["Pendiente", "Cancelado"], maxSize:255
    pays nullable:true
  }

  def beforeInsert() {
    //TODO: This is not working if i create a saleToClient instance with out set all its properties
    //println typeOfPurchase
    if (typeOfPurchase == "Contado") {
      status = "Cancelado"
    } else {
      status = "Pendiente"
    }
  }

  static mapping = {
    version false
  }

  static hasMany = [pays:Pay]

  String toString() { client }
}
