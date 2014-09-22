<div class="form-group">
	<label for="product" class="sr-only">Producto</label>
	<g:select name="product" from="${products}" value="${products.find { it.name == product} }" noSelection="['':'Seleccionar producto']" class="form-control"/>
</div>
