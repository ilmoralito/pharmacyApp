<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Notificaciones</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="col-md-12">
		<ul class="nav nav-tabs">
			<li class="${(actionName == 'quantity') ? 'active' : ''}">
				<g:link controller="notifications" action="quantity">Existencias bajas</g:link>
			</li>

			<li class="${(actionName == 'expire') ? 'active' : ''}">
				<g:link controller="user" action="expire">Por vencerse</g:link>
			</li>

			<li class="${(actionName == 'expire') ? 'active' : ''}">
				<g:link controller="user" action="expire">Pedidos pendientes</g:link>
			</li>

			<li class="${(actionName == 'expire') ? 'active' : ''}">
				<g:link controller="user" action="expire">Pagos de clientes pendientes</g:link>
			</li>
		</ul>
	</div>
	<div class="col-md-8">
		<g:if test="${quantityInstance}">
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
					<g:each in="${quantityInstance}" var="q">
						<tr>
							<td><g:link action="" params="[id:q.id]">${q.product}</g:link></td>
							<td>${q.quantity}</td>
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