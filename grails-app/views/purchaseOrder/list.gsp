<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ordenes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app, modal"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<g:link action="create" class="btn btn-primary pull-right">Crear orden de compra</g:link>
		</div>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel"><div id="invoiceModal"></div></h4>
				</div>
				<div class="modal-body">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td style="border:0;">Proveedor</td>
								<td id="providerModal" style="border:0;"></td>
							</tr>
							<tr>
								<td>Fecha de solicitud</td>
								<td id="payDateModal"></td>
							</tr>
							<tr>
								<td>Fecha de pago</td>
								<td id="dutyDateModal"></td>
							</tr>
							<tr>
								<td>Saldo</td>
								<td id="balanceModal"></td>
							</tr>
							<tr>
								<td>Tipo de compra</td>
								<td id="typeOfPurchaseModal"></td>
							</tr>
							<tr>
								<td>Estado</td>
								<td id="statusModal"></td>
							</tr>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<a id="idModal" class="btn btn-primary">Editar</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-9">
			<g:if test="${orders}">
				<table class="table">
					<thead>
						<th>#</th>
					</thead>
					<tbody>
						<g:each in="${orders}" var="order">
							<tr>
								<td>
									<a href="#" id="modalOrders" data-id="${order.id}" data-invoice="${order.invoiceNumber}" data-toggle="modal" data-target="#myModal" data-provider="${order.provider}" data-paydata="${order.dateCreated}" data-date="${order.dutyDate}" data-balance="${order.balance}" data-type="${order.typeOfPurchase}" data-status="${order.status}">
										${order.provider}, #${order.invoiceNumber}
									</a>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<p>Nada que mostrar</p>
			</g:else>
		</div>
		<div class="col-md-3">
			<h4>Filtrar</h4>
			<h5>Proveedores</h5>
			<g:form action="list">
				<g:each in="${providers}" var="provider">
					<div class="checkbox">
						<label>
							<g:checkBox name="providers" value="${provider.id}" checked="${params?.providers?.contains(provider.id.toString()) ? 'true' : 'false'}"/>
							${provider}
						</label>
					</div>
				</g:each>

				<g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
