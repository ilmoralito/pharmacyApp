<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Seleccionar cliente</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, adding, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<h4>Selecciona cliente y tipo de pago</h4>

			<g:form>
				<div class="newClient">
					<div class="text-right"><a href="#" id="trigger" class="btn btn-link btn-xs">Agregar nuevo cliente</a></div>
					<div id="box">
						<div class="form-group">
							<g:textField name="fullName" id="fullName" class="form-control" placeholder="Nombre completo" autofocus="true" data-toggle="tooltip" title="Nombre completo"/>
						</div>
						<div class="form-group">
							<g:textField name="address" id="address" class="form-control" placeholder="Direccion" data-toggle="tooltip" title="Direccion"/>
						</div>
						<div class="form-group">
							<g:textField name="identificationCard" id="identificationCard" class="form-control" placeholder="Cedula" data-toggle="tooltip" title="Cedula"/>
						</div>
						<a href="#" id="sendClient" type="submit" class="btn btn-primary pull-right">Agregar</a>
						<br>
					</div>
				</div>
				<div class="form-group">
					<label for="client">Cliente</label>
					<g:select name="client.id" from="${clients}" value="${client?.id}" optionKey="id" class="form-control"/>
				</div>

				<div class="form-group">
					<label for="typeOfPurchase">Tipo de compra</label>
					<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${typeOfPurchase}" class="form-control"/>
				</div>

				<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
				<g:link event="cancel" class="btn btn-default">Cancelar</g:link>
			</g:form>
		</div>
		<div class="col-md-6">
			<h4>Venta general</h4>
			<g:link event="confirmGeneralSale" class="btn btn-primary">Confirmar venta general</g:link>
		</div>
	</div>
</body>
</html>