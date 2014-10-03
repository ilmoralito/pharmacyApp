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
			<h4>Productos con 3 meses o menos por vencerse</h4>
			<table class="table">
				<thead>
					<tr>
						<th>Producto</th>
						<th>Vencimiento</th>
						<th>proveedor</th>
						<th>Existencia</th>
						<th>Dias Restantes</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${infoInstance}" var="info">
						<tr>
							<td><g:link action="" params="[id:info.id]">${info.product}</g:link></td>
							<td>${info.bash.format("dd-MM-yyyy")}</td>
							<td>${info.product.provider.name}</td>
							<td>${info.quantity}</td>
							<td>${info.bash - today}</td>
						</tr>
						</g:each>
					</tbody>
			</table>
		</g:if>
		<g:else>
			<br>
			<div class="alert alert-info">
				No hay productos proximos a vencerse....
				verfique las otras notificaciones
			</div>
		</g:else>
	</div>
</body>
</html>