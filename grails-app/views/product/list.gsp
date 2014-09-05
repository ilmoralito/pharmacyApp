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

	<h4>${provider}</h4>
	<g:if test="${products}">
		<table class="table">
			<thead>
				<th width="1">Codigo</th>
				<th>Producto</th>
				<th></th>
			</thead>
			<tbody>
				<g:each in="${products}" var="product">
					<tr>
						<td>
							<g:link action="show" params="[id:product.id]">
								${product.code}
							</g:link>
						</td>
						<td>
							${product.name}
						</td>
						<td>
							<g:link controller="presentation" action="list" params="[params]" class="pull-right">Presentaciones</g:link>
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
