package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService implements Serializable {

  def calTotal(Integer quantity, BigDecimal purchasePrice) {
  	quantity + purchasePrice
  }

  def calBalance(BigDecimal balance, BigDecimal total) {
  	balance + total
  }

  /*
	validProduct
	Check product to sale cumple with following constraints
	1. product has to be in a purchaseOrder in active state
	2. product quantity has to be less or equal than request
	3. check lote is valid or is in range
	The result 
	true  -> is a valid product and pass trought all constraints
	false -> is no valid product it fallo in any constraint
  */
  Boolean checkValidProduct(Product product, Integer saleQuantity) {
  	def minDateToSale = new Date() + 60 //~two months
  	def query = Item.where {
  		product == product
  	}

  	def constraintsQuery = query.where {
      purchaseOrder.status == true &&
      quantity <= saleQuantity &&
      bash <= minDateToSale
  	}

    def result= constraintsQuery.find()

    result ? true : false
  }
}
