<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Marcas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="listNavbar"/>
	<g:render template="statusButtonGroup"/>

	<g:if test="${brands}">
		<table class="table table-hover">
			<tbody>
				<g:each in="${brands}" var="brand">
					<tr>
						<td><g:link action="show" params="[id:brand.id]">${brand.name}</g:link></td>
						<td>
							<g:link controller="brand" action="list" params="[id:brand.id]" class="pull-right">
								Marcas y detalles
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
