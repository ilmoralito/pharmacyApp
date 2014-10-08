package ni.sb

class AppTagLib {
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
}
