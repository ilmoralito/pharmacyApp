<div class="well well-sm">
	<g:set var="totalMedicine" value="${medicinesToSale?.total?.sum() ?: 0}"/>
	<g:set var="totalProduct" value="${productsToSale?.total?.sum() ?: 0}"/>
	<g:set var="totalBrand" value="${brandsToSale?.total?.sum() ?: 0}"/>
	
	Saldo: ${ totalMedicine + totalProduct + totalBrand }
</div>