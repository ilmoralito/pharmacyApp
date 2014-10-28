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
					<td>
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
