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
			<div class="pull-right">
				<g:link action="create" class="btn btn-primary">Crear orden</g:link>
			</div>
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
								<td style="border:0;">Comprado a</td>
								<td id="storeModal" style="border:0;"></td>
							</tr>
							<tr>
								<td>Proveedor</td>
								<td id="providerModal"></td>
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
					<colgroup>
						<col span="1" style="width: 5%;">
						<col span="1" style="width: 10%;">
						<col span="1" style="width: 42.5%;">
						<col span="1" style="width: 42.5%;">
					</colgroup>
					<thead>
						<th>#</th>
						<th>Factura</th>
						<th>Comprado a</th>
						<th>Proveedor(es)</th>
					</thead>
					<tbody>
						<g:each in="${orders}" var="order" status="index">
							<tr>
								<td>${index + 1}</td>
								<td>
									<a href="#" id="modalOrders" data-store="${order.store}" data-id="${order.id}" data-invoice="${order.invoiceNumber}" data-toggle="modal" data-target="#myModal" data-provider="${order.providers.join(', ')}" data-paydata="${order.dateCreated}" data-date="${order.dutyDate}" data-balance="${order.balance}" data-type="${order.typeOfPurchase}" data-status="${order.status}">
										#${order.invoiceNumber}
									</a>
								</td>
								<td>${order.store}</td>
								<td>${order.providers.name.join(", ")}</td>
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
			<g:form action="list">
				<h5>Distribuidor</h5>
				<div class="form-group">
					<select name="stores" id="stores" class="form-control" multiple>
						<g:each in="${stores}" var="store">
							<option value="${store}" ${store in params?.stores ? 'selected' : ''}>${store}</option>
						</g:each>
					</select>
				</div>

				<!--
				<h5>Proveedores</h5>
				<g:each in="${providers}" var="provider">
					<div class="checkbox">
						<label>
							<g:checkBox name="providers" value="${provider.id}" checked="${params?.providers?.contains(provider.id.toString()) ? 'true' : 'false'}"/>
							${provider}
						</label>
					</div>
				</g:each>
				-->

				<h5>Tipo de pago</h5>
				<div class="checkbox">
					<label>
						<g:checkBox name="cash" value="Contado" checked="${params?.cash ? 'true' : 'false'}"/>
						Contado
					</label>
				</div>
				<div class="checkbox">
					<label>
						<g:checkBox name="credit" value="Credito" checked="${params?.credit ? 'true' : 'false'}"/>
						Credito
					</label>
				</div>

				<h5>Estado</h5>
				<div class="checkbox">
					<label>
						<g:checkBox name="pending" value="false" checked="${params?.pending ? 'true' : 'false'}"/>
						Pendiente
					</label>
				</div>
				<div class="checkbox">
					<label>
						<g:checkBox name="canceled" value="true" checked="${params?.canceled ? 'true' : 'false'}"/>
						Cancelado
					</label>
				</div>

				<g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
