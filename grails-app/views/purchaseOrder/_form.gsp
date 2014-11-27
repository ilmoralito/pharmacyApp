<div class="form-group">
	<div class="form-group">
		<label for="store" class="sr-only">Comprado en</label>
		<g:textField name="store" class="form-control" placeholder="Comprado en" list="stores" autofocus="true"/>
		<datalist id="stores">
			<g:each in="${stores}" var="provider">
				<option value="${provider}">
			</g:each>
		</datalist>
	</div>

	<label for="providers" class="sr-only">Proveedores</label>
	<!--<g:select name="provider" from="${providers}" optionKey="id" value="${purchaseOrder?.provider?.id}" noSelection="[null:'Selecciona proveedor']" class="form-control"/>-->
	<g:each in="${providers}" var="provider">
		<div class="checkbox">
			<label>
				<g:checkBox name="providers" value="${provider}" checked="false"/>
				${provider}
			</label>
		</div>
	</g:each>
	<g:if test="${purchaseOrder?.provider?.id}">
		<span class="help-block">Si cambia de proveedor los productos deberan ser reiniciados</span>
	</g:if>
</div>
<div class="form-group">
	<label for="dutyDate" class="sr-only">Fecha tope de pago</label>
	<g:textField name="dutyDate" value="${g.formatDate(date:purchaseOrder?.dutyDate, formatName:'custom.date.format')}" class="form-control" placeholder="Fecha tope de pago"/>
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
		<select name="status" id="status" class="form-control">
			<g:each in="${[true, false]}" var="option">
				<option value="${option}" ${purchaseOrder?.status ? 'Selected' : ''}>
					${option ? "Cancelado" : "Pendiente"}
				</option>
			</g:each>
		</select>
	</div>
</g:if>
