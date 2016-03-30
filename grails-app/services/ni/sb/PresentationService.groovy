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

    def getMeasuresByPresentation(Integer presentationId) {
        def presentation = Presentation.get presentationId

        if (!presentation) {
            null
        } else {
            presentation.measures
        }
    }
}
