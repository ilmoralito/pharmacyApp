<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Cliente</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<h4>Actualizar ${client?.fullName}</h4>
	<div class="row">
		<div class="col-md-4">
			<g:form action="update" autocomplete="off">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Actualizar" class="btn btn-primary"/>
			</g:form>
			<g:render template="/layouts/errorsMessage" model="[instance:client]"/>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
			<g:form action="addTelephone">
				<div class="form-group">
					<label for="phone" class="sr-only">Telefono</label>
					<g:textField name="phone" class="form-control" placeholder="Telefono"/>
				</div>
				<g:submitButton name="send" value="Agregar telefono" class="btn btn-default"/>
			</g:form>
		</div>
	</div>
</body>
</html>