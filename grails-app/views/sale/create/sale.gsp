<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<g:link event="cancel" class="btn btn-warning">Cancelar</g:link>
			</div>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-8">
			<g:render template="sales"/>
		</div>

		<div class="col-md-4">
			<ul class="nav nav-tabs">
				<li class="active">
					<g:link>General</g:link>
				</li>
				<li>
					<g:link event="saleToClient">Cliente</g:link>
				</li>
			</ul>
			<br>

			<g:form>
				<g:render template="form"/>
				<g:submitButton name="selectProduct" value="Mostrar" class="btn btn-primary btn-block"/>
			</g:form>
			<g:if test="${productsInStock}">
				<g:render template="create/productsInStock"/>
			</g:if>
		</div>
	</div>
</body>
</html>
