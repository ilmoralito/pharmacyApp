<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Proveedor</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<div class="row">
		<div class="col-md-6">
			<h4>Proveedor</h4>
			<g:form autocomplete="off">
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Agregar proveedor y continuar" class="btn btn-default"/>
			</g:form>

			<g:render template="/layouts/errorsMessage" model="[instance:errors]"/>
		</div>
</body>
</html>