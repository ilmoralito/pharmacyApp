<div class="form-group">
	<label for="product" class="sr-only">Producto</label>
	<g:select name="product" from="${products}" optionKey="id" noSelection="['':'Seleccionar producto']" class="form-control"/>
</div>
<div class="form-group">
	<label for="presentation" class="sr-only">Presentacion</label>
	<g:select name="presentation" from="" class="form-control"/>
</div>
<div class="form-group">
	<label for="measure" class="sr-only">Medida</label>
	<g:select name="measure" from="" class="form-control"/>
</div>
<div class="form-group">
	<label for="quantity" class="sr-only">Cantidad</label>
	<g:textField name="quantity" class="form-control" placeholder="Cantidad"/>
</div>
