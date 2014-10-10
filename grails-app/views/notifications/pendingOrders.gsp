<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Notificaciones</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="notifications"/>
	<div class="col-md-12">
		<g:if test="${infoInstance}">
			<br>
			<div class="col-md-9">
				<h4>Pedidos pendientes de pago</h4>
			</div>
			<div class="col-md-3">
				<g:link controller="notifications" action="pendingOrders" params="[f:'pdf']" class="">
					<span class="glyphicon glyphicon-print"></span> PDF
				</g:link>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Proveedor</th>
						<th>Fecha de Pago</th>
						<th>Dias Restantes</th>
						<th>Total a Pagar</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${infoInstance}" var="info">
						<tr>
							<td><g:link action="" params="[id:info.id]">${info.items.product.provider}</g:link></td>
							<td>${info.dutyDate.format("dd-MM-yyyy")}</td>
							<td>${info.dutyDate - today}</td>
							<td>${info.balance}</td>
						</tr>
						</g:each>
					</tbody>
			</table>
		</g:if>
		<g:else>
			<br>
			<div class="alert alert-info">
				No hay pedidos pendientes de pagos....
				verfique las otras notificaciones
			</div>
		</g:else>
	</div>
</body>
</html>