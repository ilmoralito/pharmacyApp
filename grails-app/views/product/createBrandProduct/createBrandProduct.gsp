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
	<ul class="nav nav-tabs">
		<li class="${actionName == 'createProduct' ? 'active' : ''}">
			<g:link event="createProduct" params="[providerId:providerId]">Producto</g:link>
		</li>
		<li class="${actionName == 'createMedicine' ? 'active' : ''}">
			<g:link event="createMedicine" params="[providerId:providerId]">Medicamento</g:link>
		</li>
		<li class="${actionName == 'createBrandProduct' ? 'active' : ''}">
			<g:link event="createBrandProduct" params="[providerId:providerId]">Marcas</g:link>
		</li>
	</ul>
	<br>

	<g:form autocomplete="off">
		<g:render template="form"/>

		<g:submitButton name="add" value="Agregar producto" class="btn btn-primary"/>
	</g:form>
	</div>
</body>
</html>
