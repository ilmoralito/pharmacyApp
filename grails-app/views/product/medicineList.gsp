<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Medicina</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="listNavbar"/>
	<g:render template="statusButtonGroup"/>

	<g:if test="${medicines}">
		<table class="table table-hover">
			<tbody>
				<g:each in="${medicines}" var="medicine">
					<tr>
						<td><g:link action="show" params="[id:medicine.id]">${medicine.name}</g:link></td>
						<td>
							<g:link controller="presentation" action="list" params="[productId:medicine.id]" class="pull-right">
								Presentaciones y medidas
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
