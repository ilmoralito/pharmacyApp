<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ordenes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:link action="create" class="btn btn-default pull-right">Crear orden de compra</g:link>
	<g:if test="${orders}">
		<table class="table">
			<thead>
				<th>Facturas</th>
			</thead>
			<tbody>
				<g:each in="${orders}" var="order">
					<tr>
						<td>
							<g:link action="show" params="[id:order.id]">
								${order.invoiceNumber}
							</g:link>
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
