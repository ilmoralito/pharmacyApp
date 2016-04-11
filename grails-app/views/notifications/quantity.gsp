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
				<h4>Productos con existencias bajas</h4>
			</div>
			<div class="col-md-3">
				<h4><g:link controller="notifications" action="quantity" params="[f:'pdf']" class="">
					<span class="glyphicon glyphicon-print"></span> PDF
				</g:link></h4>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Producto</th>
						<th>Proveedor</th>
						<th>Existencia</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${infoInstance}" var="info">
						<tr>
							<td>${info.product}</td>
							<td>${info.product.provider}</td>
							<td>${info.quantity}</td>
						</tr>
						</g:each>
					</tbody>
			</table>
		</g:if>
		<g:else>
			<br>
			<div class="alert alert-info">
				No hay productos con existencias bajas....
				verfique las otras notificaciones
			</div>
		</g:else>
	</div>
</body>
</html>