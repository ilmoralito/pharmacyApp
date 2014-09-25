<g:if test="${sales}">
	<h4>Detalle de venta</h4>
	<table class="table table-hover">
		<thead>
			<th width="1">#</th>
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
						<a href="#" data-toggle="modal" data-target="#myModal">
							${sale.item}
						</a>
						<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="myModalLabel">${sale.item}</h4>
								</div>
								<div class="modal-body">
									<table class="table">
										<tr>
											<td><strong>Precio de compra: </strong></td>
											<td>${sale.item.purchasePrice}</td>
										</tr>
										<tr>
											<td><strong>Precio de venta: </strong></td>
											<td>${sale.item.sellingPrice}</td>
										</tr>
										<tr>
											<td><strong>Fecha de vencimiento: </strong></td>
											<td><g:formatDate formatName="custom.date.format" date="${sale.item.bash}"/></td>
										</tr>
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
								</div>
								</div>
							</div>
						</div>
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
	<g:link event="confirmSale" class="btn btn-primary">Confirmar</g:link>
</g:if>
<g:else>
	<h4>...</h4>
</g:else>
