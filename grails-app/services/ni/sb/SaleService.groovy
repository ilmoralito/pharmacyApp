package ni.sb

import grails.transaction.Transactional

@Transactional
class SaleService {
	def calcSaleBalance(def medicinesToSale, def productsToSale, def brandsToSale) {
		def totalMedicine = medicinesToSale?.total?.sum() ?: 0
		def totalProduct = productsToSale?.total?.sum() ?: 0
		def totalBrand = brandsToSale?.total?.sum() ?: 0

		def total = totalMedicine + totalProduct + totalBrand

		total
	}
}
