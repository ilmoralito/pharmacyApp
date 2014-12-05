<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Crear pedido</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, createAndUpdatePurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>
	<div class="row">
		<div class="col-md-6">
			<g:form autocomplete="off">
				<g:if test="${providers}">
					<g:render template="form"/>
					<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
				</g:if>
				<g:else>
					<p>Se necesitan <g:link controller="provider" action="list">proveedores</g:link> para continuar</p>
				</g:else>
			</g:form>

			<g:render template="/layouts/errorsMessage" model="[instance:errors]"/>
		</div>
	</div>
</body>
</html>
