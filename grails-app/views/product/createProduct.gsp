<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Crear producto</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="navbar"/>

	<g:form action="createProduct" autocomplete="off">
		<g:hiddenField name="providerId" value="${providerId}"/>
		<g:render template="form"/>
		<g:submitButton name="confirm" value="Agregar" class="btn btn-primary"/>
	</g:form>
</body>
</html>
