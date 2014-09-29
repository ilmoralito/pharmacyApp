<div class="form-group">
	<label for="product">Producto</label>
	<g:select name="product.id" from="${products}" optionKey="id" value="${product?.id}" class="chosen-select" tabindex="2"/>
</div>
