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
		
		<div class="form-group" style="margin-bottom:0;">
			<label for="phones" class="sr-only">Telefono</label>
			<g:textField name="phones" class="form-control" placeholder="Telefono"/>
		</div>

		<a href="#"><small>Agregar telefono</small></a>

		<g:submitButton name="confirm" value="Agregar" class="btn btn-primary btn-block" style="margin-top:5px;"/>
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
