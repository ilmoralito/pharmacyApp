<div class="form-group">
	<label for="dutyDate" class="sr-only">Fecha tope de pago</label>
	<g:textField name="dutyDate" value="${purchaseOrder?.dutyDate?.format('yyyy-MM-dd')}" class="form-control" placeholder="Fecha tope de pago"/>
</div>
<div class="form-group">
	<label for="invoiceNumber" class="sr-only">Numero de factura</label>
	<g:textField name="invoiceNumber" value="${purchaseOrder?.invoiceNumber}" class="form-control" placeholder="Numero de factura"/>
</div>
<div class="form-group">
	<label for="typeOfPurchase" class="sr-only">Tipo de compra</label>
	<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${purchaseOrder?.typeOfPurchase}" noSelection="['':'Seleccione un tipo de pago']" class="form-control"/>
</div>
<g:if test="${actionName != 'create'}">
	<div class="form-group">
		<label for="status" class="sr-only">Estado</label>
		<!--Verifica si los valores en la lista values es el texto de el select y los valores en from son los datos pasados-->
		<g:select name="status" from="[true, false]", values="['Activo', 'Inactivo']" value="${purchaseOrder?.status}" noSelection="['':'Seleccione un estado']" class="form-control"/>
	</div>
</g:if>
