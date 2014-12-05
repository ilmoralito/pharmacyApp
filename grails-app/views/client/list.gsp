<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Clientes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:form action="save" autocomplete="off">
		<g:render template="form"/>
		<g:submitButton name="confirm" value="Agregar" class="btn btn-primary btn-block"/>
	</g:form>
	<g:render template="/layouts/errorsMessage" model="[instance:client]"/>
	<br>
	
	<div class="btn-group btn-group-justified">
		<g:link action="list" class="btn btn-default ${!params?.status || paras?.status == true ? 'active' : ''}">
			Activos
		</g:link>
		<g:link action="list" params="[status:false]" class="btn btn-default ${params?.status == 'false' ? 'active' : ''}">
			Inactivos
		</g:link>
	</div>
</body>
</html>
