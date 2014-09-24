<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Proveedores</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:if test="${providers}">
		<table class="table">
			<thead>
				<th>Proveedor</th>
				<th></th>
			</thead>
			<tbody>
				<g:each in="${providers}" var="provider">
					<tr>
						<td>
							<g:link action="show" params="[id:provider.id]">
								${provider}
							</g:link>
						</td>
						<td>
							<g:link controller="product" action="list" params="[providerId:provider.id]" class="pull-right">
								<g:if test="${!provider.products}">*</g:if>
								Administrar productos
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
