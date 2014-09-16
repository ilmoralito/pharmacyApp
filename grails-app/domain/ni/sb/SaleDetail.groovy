package ni.sb

class SaleDetail implements Serializable {
  def itemService

	Product product
  Presentation presentation
  String measure
  Integer quantity
  BigDecimal total

	Date dateCreated
	Date lastUpdated

  static constraints = {
    product validator:{ product ->
      if (!itemService.checkValidProduct(product)) {
        "saleDetail.product.notMatch"
      }
    }

    presentation validator:{ presentation, saleDetail ->
      if (!saleDetail.product.presentations.contains(presentation)) {
        "saleDetail.presentation.notFound"
      }
    }

    measure validator: { measure, saleDetail ->
      if (!saleDetail.presentation.measures.contains(measure)) {
        "saleDetail.measure.notFound"
      }
    }

    quantity min:1, nullable:false
    total scale:2, min:0.1
  }

  static mapping = {
  	version false
  }

  static belongsTo = [sale:Sale]

  String toString() { product }
}
