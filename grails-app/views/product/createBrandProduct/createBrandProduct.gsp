<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Crear Producto de marca</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="createBrandProduct/nav"/>
	<br>

	<g:form autocomplete="off">
		<g:render template="form"/>

		<g:submitButton name="add" value="Agregar" class="btn btn-primary"/>
	</g:form>
	</div>
</body>
</html>
