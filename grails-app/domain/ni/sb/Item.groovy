package ni.sb

class Item implements Serializable {
  Product product
  Presentation presentation	
  String measure
  Integer quantity
  BigDecimal purchasePrice
  BigDecimal sellingPrice
  String bash
  BigDecimal total

	Date dateCreated
	Date lastUpdated

  static constraints = {
    product nullable:false
    presentation nullable:false, validator:{ presentation, item ->
      if (!item.product.presentations.contains(presentation)) {
        "notPresentationInSelectedProduct"
      }
    }
    measure nullable:false, validator: { measure, item ->
      if (!item.presentation.measures.contains(measure)) {
        "notMeasureInSelectedPresentation"
      }
    }
    quantity nullable:false, min:1
    purchasePrice nullable:false, min:0.1, scale:2, validator:{ purchasePrice, item ->
      if (purchasePrice >= item.sellingPrice) {
        "notValid"
      }
    }
    sellingPrice nullable:false, min:0.1, scale:2
    bash blank:false, unique:true
    total nullable:false
  }

  def beforeInsert() {
    /*
    def balance = purchaseOrder.balance ?: 0

    total = purchasePrice * quantity
    purchaseOrder.balance = balance + total
    */
  }

  def beforeUpdate() {
    total = purchasePrice * quantity
    purchaseOrder.balance += total
  }

  def beforeDelete() {
    //todo
  }

  static mapping = {
  	version false
  }

  static belongsTo = [purchaseOrder:PurchaseOrder]

  String toString() { product }
}
