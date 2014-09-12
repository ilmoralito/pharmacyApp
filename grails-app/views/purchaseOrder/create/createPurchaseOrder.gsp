<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Crear orden de compra y articulos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, createPurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>
	<div class="row">
		<div class="col-md-6">
			<g:form autocomplete="off">
				<g:render template="form"/>
		
				<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
</body>
</html>
