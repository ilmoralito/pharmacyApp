<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Editar Pedido</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, createPurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>
	<div class="row">
		<div class="col-md-6">
			<g:form autocomplete="off" action="update">
				<g:hiddenField name="id" value="${purchaseOrder.id}"/>
				<g:render template="form"/>
				<g:submitButton name="editPurchaseOrder" value="Actualizar" class="btn btn-default"/>
				<g:link action="edit" class="btn btn-primary" id="${purchaseOrder.id}">Editar Items</g:link>
			</g:form>

			<g:render template="/layouts/errorsMessage" model="[instance:errors]"/>
		</div>
	</div>
</body>
</html>