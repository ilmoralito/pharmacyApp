<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Presentaciones de ${product}</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:if test="${presentations}">
		<table class="table table-hover">
			<thead>
				<th>Presentacion</th>
				<th width="1"></th>
			</thead>
			<tbody>
				<g:each in="${presentations}" var="presentation">
					<tr>
						<td>${presentation}</td>
						<td>
							<g:link action="delete" params="[id:presentation.id]">
								<span class="glyphicon glyphicon-trash"></span>
							</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</body>
</html>
