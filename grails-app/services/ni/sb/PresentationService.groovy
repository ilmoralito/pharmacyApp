package ni.sb

import grails.transaction.Transactional

@Transactional
class PresentationService {

  def presentationsByProduct(Integer productId) {
  	def product = Product.get productId

  	if (!product) {
  		null
  	} else {
  		product.presentations
  	}
  }
}
