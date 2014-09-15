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
					<g:link action="list" class="btn btn-default">Activas</g:link>
					<g:link action="list" class="btn btn-default">Inactivas</g:link>
				</div>
				<div class="btn-group">
					<g:link action="list" class="btn btn-default">Credito</g:link>
					<g:link action="list" class="btn btn-default">Contado</g:link>
				</div>
				<g:link action="create" class="btn btn-primary">Crear orden de compra</g:link>
			</div>
		</div>
	</div>

	<g:if test="${orders}">
		<table class="table">
			<thead>
				<th width="1">Facturas</th>
				<th>Fecha de pedido</th>
			</thead>
			<tbody>
				<g:each in="${orders}" var="order">
					<tr>
						<td>
							<g:link action="show" params="[id:order.id]">
								${order.invoiceNumber}
							</g:link>
						</td>
						<td>${order.dateCreated.format("yyyy-MM-dd")}</td>
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
