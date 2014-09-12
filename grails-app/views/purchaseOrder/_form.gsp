<div class="form-group">
	<label for="deadline" class="sr-only">Fecha tope de pago</label>
	<g:textField name="deadline" value="${purchaseOrder?.deadline?.format('yyyy-MM-dd')}" class="form-control" placeholder="Fecha tope de pago"/>
</div>
<div class="form-group">
	<label for="invoiceNumber" class="sr-only">Numero de factura</label>
	<g:textField name="invoiceNumber" value="${purchaseOrder?.invoiceNumber}" class="form-control" placeholder="Numero de factura"/>
</div>
<div class="form-group">
	<label for="typeOfPurchase" class="sr-only">Tipo de compra</label>
	<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${purchaseOrder?.typeOfPurchase}" noSelection="['':'Seleccione un tipo de pago']" class="form-control"/>
</div>
