<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Editar pedido</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, createAndUpdatePurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>

	<div class="row">
		<div class="col-md-6">
			<g:form autocomplete="off">
				<g:render template="form"/>
		
				<g:submitButton name="confirm" value="Confirmar edicion de pedido" class="btn btn-primary"/>
			</g:form>

			<g:render template="/layouts/errorsMessage" model="[instance:errors]"/>
		</div>
	</div>
</body>
</html>
