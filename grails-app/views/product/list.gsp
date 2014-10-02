<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Productos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	
	<g:if test="${products}">
		<table class="table table-hover">
			<thead>
				<th colspan="2">Producto</th>
			</thead>
			<tbody>
				<g:each in="${products}" var="product">
					<tr>
						<td><g:link action="show" params="[id:product.id]">${product.name}</g:link></td>
						<td>
							<g:if test="${product instanceof ni.sb.Medicine}">
								<g:link controller="presentation" action="list" params="[productId:product.id]" class="pull-right">
									Presentaciones y medidas
								</g:link>
							</g:if>
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
