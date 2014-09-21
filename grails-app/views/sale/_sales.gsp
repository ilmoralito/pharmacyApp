<g:if test="${sales}">
	<h4>Detalle de venta</h4>
	<table class="table table-striped">
		<thead>
			<th width="1"></th>
			<th>Producto</th>
			<th>Presentacion</th>
			<th>Medida</th>
			<th>Cantidad</th>
			<th><span class="pull-right">Total</span></th>
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
					<td><span class="pull-right">${sale.total}</span></td>
					<td>
						<g:link event="delete" params="[index:index]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</td>
				</tr>
			</g:each>
			<tr>
				<td colspan="6">
					<span class="pull-right">${sales.total.sum()}</span>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
	<g:submitButton name="completeSale" value="Confirmar" class="btn btn-primary"/>
</g:if>
<g:else>
	<h4>...</h4>
</g:else>
