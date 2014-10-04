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
	<g:render template="listNavbar"/>
	<g:render template="statusButtonGroup"/>

	<g:if test="${products}">
		<table class="table table-hover">
			<tbody>
				<g:each in="${products}" var="product">
					<tr>
						<td><g:link action="show" params="[id:product.id]">${product.name}</g:link></td>
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
