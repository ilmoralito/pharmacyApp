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
	<div class="col-md-8">
		<g:if test="${infoInstance}">
			<br>
			<h4>Productos con existencias bajas</h4>
			<table class="table">
				<thead>
					<tr>
						<th>Producto</th>
						<th>Existencia</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${infoInstance}" var="info">
						<tr>
							<td><g:link action="" params="[id:info.id]">${info.product}</g:link></td>
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