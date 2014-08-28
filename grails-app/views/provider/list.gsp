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
		<g:each in="${providers}" var="provider">
			<h4><g:link action="show" id="${provider.id}">${provider}</g:link></h4>
			<p>${provider.contactInformation()}</p>
			<br>
		</g:each>
	</g:if>
</body>
</html>