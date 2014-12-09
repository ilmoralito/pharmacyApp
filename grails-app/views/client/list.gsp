<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Clientes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, addTelefone"/>
</head>
<body>
	<g:form action="save" autocomplete="off">
		<g:render template="form"/>
		
		<div class="form-group">
			<label for="phones" class="sr-only">Telefono</label>
			<input type="tel" maxlength="8" minlength="8" pattern="\d{8}" x-moz-errormessage="Dato incorrecto" name="phones" id="phones" class="form-control" placeholder="Telefono">
		</div>

		<a href="#" id="trigger"><small>Agregar telefono</small></a>

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
