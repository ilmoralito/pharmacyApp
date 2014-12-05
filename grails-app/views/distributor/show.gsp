<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Distribuidor</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<g:link action="list">Regresar a lista de distribuidores</g:link>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-6">
			<g:form action="update" autocomplete="off">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:render template="form"/>

				<g:submitButton name="update" value="Actualizar" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
</body>
</html>