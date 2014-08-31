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
			<h4>Productos de ${name}</h4>
			<g:form autocomplete="off">
				<g:render template="formProduct"/>
				<g:submitButton name="addProduct" value="Agregar producto" class="btn btn-default"/>
				<g:link event="confirm" class="btn btn-primary pull-right">Confirmar</g:link>
			</g:form>

			<g:render template="productsList"/>

			<g:render template="/layouts/errorsMessage" model="[instance:errors]"/>
		</div>
</body>
</html>