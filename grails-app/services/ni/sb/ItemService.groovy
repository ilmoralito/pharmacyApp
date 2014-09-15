package ni.sb

import grails.transaction.Transactional

@Transactional
class ItemService {

  def calTotal(Integer quantity, BigDecimal purchasePrice) {
  	quantity + purchasePrice
  }

  def calBalance(BigDecimal balance, BigDecimal total) {
  	balance + total
  }
}
