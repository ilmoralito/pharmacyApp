<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Clientes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<h4>Crear cliente</h4>
	<g:form action="save" autocomplete="off">
		<g:render template="form"/>
		<g:submitButton name="confirm" value="Agregar" class="btn btn-primary btn-block"/>
	</g:form>
	<g:render template="/layouts/errorsMessage" model="[instance:client]"/>
	<br>
	<g:link action="list" class="btn btn-default btn-block ${!params?.status || paras?.status == true ? 'active' : ''}">Activos</g:link>
	<g:link action="list" params="[status:false]" class="btn btn-default btn-block ${params?.status == 'false' ? 'active' : ''}">Inhactivos</g:link>
</body>
</html>