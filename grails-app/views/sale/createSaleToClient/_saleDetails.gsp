<!--Medicines-->
<g:if test="${medicinesToSale}">
	<h4>Medicinas</h4>

	<table class="table">
		<thead>
			<th>Nombre</th>
			<th>Presentacion</th>
			<th>Medida</th>
			<th>Cantidad</th>
			<th>Total a pagar</th>
			<th width="1"></th>
		</thead>
		<tbody>
			<g:each in="${medicinesToSale}" var="medicine" status="index">
				<tr>
					<td>${medicine.item.product.name}</td>
					<td>${medicine.item.presentation}</td>
					<td>${medicine.item.measure}</td>
					<td>${medicine.quantity}</td>
					<td>${medicine.total}</td>
					<td width="1">
						<g:link event="deleteItem" params="[index:index]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</td>
				</tr>
			</g:each>
			<tr>
				<td colspan="4">TOTAL</td>
				<td colspan="2">${medicinesToSale.total.sum()}</td>
			</tr>
		</tbody>
	</table>
</g:if>

<g:if test="${productsToSale}">
	<h4>Productos</h4>

	<table class="table">
		<thead>
			<th>Producto</th>
			<th>Cantidad</th>
			<th>Total a pagar</th>
			<th></th>
		</thead>
		<tbody>
			<g:each in="${productsToSale}" var="product" status="index">
				<tr>
					<td>${product}</td>
					<td>${product.quantity}</td>
					<td>${product.total}</td>
					<td width="1">
						<g:link event="deleteItem" params="[index:index]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</td>
				</tr>
			</g:each>
			<tr>
				<td colspan="2">TOTAL</td>
				<td colspan="2">${productsToSale.total.sum()}</td>
			</tr>
		</tbody>
	</table>
</g:if>
