<div class="form-group">
	<label for="product" class="sr-only">Producto</label>
	<g:select name="product.id" from="${products}" optionKey="id" value="${product?.id}" noSelection="['':'Seleccionar producto']" class="form-control"/>
</div>
