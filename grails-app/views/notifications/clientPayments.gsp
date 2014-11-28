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
				<h4>Clientes con cuentas pendientes</h4>
			</div>
			<div class="col-md-3">
				<h4><g:link controller="notifications" action="clientPayments" params="[f:'pdf']" class="">
					<span class="glyphicon glyphicon-print"></span> PDF
				</g:link></h4>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Cliente</th>
						<th>Fecha de compra</th>
						<th>Fecha de pago</th>
						<th>Abonado</th>
						<th>Saldo Actual</th>
						<th>Total a Pagar</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${infoInstance}" var="info">
						<tr>
							<td>${info.client}</td>
							<td>${info.dateCreated.format("dd-MM-yyyy")}</td>
							<td>${info.proyectionDate.format("dd-MM-yyyy")}</td>
							<td>${info.totalPayment}</td>
							<td>${info.currentBalance}</td>
							<td>${info.balance}</td>
						</tr>
						</g:each>
					</tbody>
			</table>
			</table>
		</g:if>
		<g:else>
			<br>
			<div class="alert alert-info">
				No hay clientes con pagos proximos a vencer....
				verfique las otras notificaciones
			</div>
		</g:else>
	</div>
</body>
</html>