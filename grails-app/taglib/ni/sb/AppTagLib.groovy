package ni.sb

class AppTagLib {
	def saleService

  static defaultEncodeAs = "html"
  static encodeAsForTags = [getSaleBalance: 'raw']

  static namespace = "pharmacyApp"

  def purchaseOrderStatus = { attrs, body ->
		if (attrs.status) {
			out << "Pagado"
		} else {
			out << "Pendiente"
		}
	}

	def getSaleBalance = { attrs ->
		def balance = saleService.calcSaleBalance(attrs.medicinesToSale, attrs.productsToSale, attrs.brandsToSale)

		out << render(template:"createSaleToClient/balanceTemplate", model:[balance:balance])
	}

	def calcReceiptNumber = { attrs ->
		def payInstance = Pay.list(sort: "receiptNumber", order: "asd"), lastPay, receiptNumber
		if (payInstance.size() > 0) {
			lastPay = payInstance.last()
			receiptNumber = lastPay.receiptNumber + 1
		}else{
			receiptNumber = 1
		}

		out << receiptNumber
	}
}
