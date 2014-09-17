<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, sale, jquery-ui"/>
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
		<div class="col-md-9">
			<g:render template="sales"/>
		</div>

		<div class="col-md-3">
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
				<g:submitButton name="confirm" value="Agregar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
