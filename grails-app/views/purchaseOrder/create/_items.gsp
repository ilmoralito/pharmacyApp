<g:if test="${medicines}">
	<h4>Medicamentos</h4>
	<table class="table table-hover">
		<thead>
			<th>Producto</th>
			<th>Present.</th>
			<th>Med.</th>
			<th>Cant.</th>
			<th>Compra</th>
			<th>Venta</th>
			<th>FV</th>
			<th>Total</th>
			<th width="1"></th>
		</thead>
		<tbody>
			<g:each in="${medicines}" var="medicine" status="index">
				<tr>
					<td>${medicine.product.name}</td>
					<td>${medicine.presentation}</td>
					<td>${medicine.measure}</td>
					<td>${medicine.quantity}</td>
					<td>${medicine.purchasePrice}</td>
					<td>${medicine.sellingPrice}</td>
					<td>
						<g:formatDate date="${medicine.bash}" formatName="fv.date.format"/>
					</td>
					<td>
						<g:formatNumber number="${medicine.total}" formatName="default.number.decimal"/>
					</td>
					<td>
						<g:link event="deleteMedicine" params="[index:index]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</td>
				</tr>
			</g:each>
			<tr>
				<td>TOTAL</td>
				<td colspan="7">
					<div class="pull-right">
						<g:formatNumber number="${medicines.total.sum() ?: 0}" formatName="default.number.decimal"/>
					</div>
				</td>
				<td width="1"></td>
			</tr>
		</tbody>
	</table>
</g:if>

<g:if test="${brandProducts}">
	<h4>Productos con marca</h4>
	<table class="table table-hover">
		<thead>
			
		</thead>
		<tbody>
			
		</tbody>
	</table>
</g:if>

<g:if test="${medicines || products || brands}">
	<g:link event="conplete" class="btn btn-primary">
		Completar proceso con balance: <g:formatNumber number="${purchaseOrder?.balance ?: 0}" formatName="default.number.decimal"/>
	</g:link>
</g:if>
