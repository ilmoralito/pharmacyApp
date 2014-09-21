<g:if test="${sales}">
	<h4>Detalle de venta</h4>
	<table class="table table-striped">
		<thead>
			<th width="1"></th>
			<th>Producto</th>
			<th>Presentacion</th>
			<th>Medida</th>
			<th>Cantidad</th>
			<th>Total</th>
			<th width="1"></th>
		</thead>
		<tbody>
			<g:each in="${sales}" var="sale" status="index">
				<tr>
					<td>${index + 1}</td>
					<td>
						<a href="#" data-item="${sale.item}" data-purchasePrice="${sale.item.purchasePrice}" data-sellingPrice="${sale.item.sellingPrice}" data-bash="${sale.item.bash}">
							${sale.item}
						</a>
					</td>
					<td>${sale.presentation}</td>
					<td>${sale.measure}</td>
					<td>${sale.quantity}</td>
					<td>${sale.total}</td>
					<td>
						<g:link action="actionName"><span class="glyphicon glyphicon-trash"></span></g:link>
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
	<g:submitButton name="completeSale" value="Confirmar" class="btn btn-primary"/>
</g:if>
<g:else>
	<h4>...</h4>
</g:else>
