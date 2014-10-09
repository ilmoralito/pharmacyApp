<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ordenes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<div class="btn-group">
					<g:link action="list" params="[option:"true"]" class="btn btn-default">Pagados</g:link>
					<g:link action="list" params="[option:"false"]" class="btn btn-default">Pendientes</g:link>
				</div>
				<div class="btn-group">
					<g:link action="list" params="[option:"Credito"]" class="btn btn-default">Credito</g:link>
					<g:link action="list" params="[option:"Contado"]" class="btn btn-default">Contado</g:link>
				</div>
				<g:link action="create" class="btn btn-primary">Crear orden de compra</g:link>
			</div>
		</div>
	</div>

	<g:if test="${orders}">
		<table class="table">
			<thead>
				<th>#</th>
			</thead>
			<tbody>
				<g:each in="${orders}" var="order">
					<tr>
						<td>
							<a href="#" data-toggle="modal" data-target="#myModal">
								${order.provider}, #${order.invoiceNumber}
							</a>
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title" id="myModalLabel">${order.invoiceNumber}</h4>
										</div>
										<div class="modal-body">
											<table class="table">
												<tr>
													<td><strong>Proveedor: </strong></td>
													<td>${order.provider}</td>
												</tr>
												<tr>
													<td><strong>Fecha de pago: </strong></td>
													<td><g:formatDate formatName="custom.date.format" date="${order.dutyDate}"/></td>
												</tr>
												<tr>
													<td><strong>Saldo: </strong></td>
													<td>${order.balance}</td>
												</tr>
												<tr>
													<td><strong>Tipo de compra: </strong></td>
													<td>${order.typeOfPurchase}</td>
												</tr>
												<tr>
													<td><strong>Estado: </strong></td>
													<td>${order.status}</td>
												</tr>
											</table>
										</div>
										<div class="modal-footer">
											<g:link action="show" class="btn btn-primary" params="[id:order.id]">Editar</g:link>
											<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<p>Nada que mostrar</p>
	</g:else>
</body>
</html>
