package ni.sb

class AppTagLib {
	def saleService

  static defaultEncodeAs = 'html'
  //static encodeAsForTags = [tagName: 'raw']

  static namespace = "pharmacyApp"

  def purchaseOrderStatus = { attrs, body ->
		if (attrs.status) {
			out << "Pagado"
		} else {
			out << "Pendiente"
		}
	}

	def getSaleBalance = { attrs ->
		def medicinesToSale = attrs.medicinesToSale
		def productsToSale = attrs.productsToSale
		def brandsToSale = attrs.brandsToSale

		out << saleService.calcSaleBalance(medicinesToSale, productsToSale, brandsToSale)
	}
}
