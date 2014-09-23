<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas por cliente</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app, saleFilter, jquery-ui"/>
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
				<li>
					<g:link event="sale">General</g:link>
				</li>
				<li class="active">
					<g:link>Cliente</g:link>
				</li>
			</ul>
			<br>

			<g:form>
				<div class="form-group">
					<label for="client">Cliente</label>
					<g:select name="client.id" from="${clients}" optionKey="id" value="${client?.id}" class="chosen-select" tabindex="2"/>
				</div>
				<div class="form-group">
					<label for="typeOfPurchase" class="sr-only">Tipo de compra</label>
					<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${typeOfPurchase}" noSelection="['':'Tipo de pago']" class="form-control"/>
				</div>
				<g:submitButton name="chooseClient" value="Seleccionar cliente y tipo de pago" class="btn btn-primary btn-block"/>
			</g:form>
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
