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
			<g:form action="update" autocomplete="off">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Confirmar" class="btn btn-default"/>
			</g:form>

			<g:render template="/layouts/errorsMessage" model="[instance:provider]"/>
		</div>
	</div>
</body>
</html>