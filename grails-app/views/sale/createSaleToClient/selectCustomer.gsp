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
			<h4>Venta a cliente</h4>

			<g:form>
				<div class="form-group">
					<small class="help-block"><a href="#" id="trigger">Agregar nuevo cliente</a></small>
					<div id="box">
						<div class="form-group">
							<g:textField name="fullName" id="fullName" class="form-control input-sm" placeholder="Nombre completo" autofocus="true" data-toggle="tooltip" title="Nombre completo"/>
						</div>
						<div class="form-group">
							<g:textField name="address" id="address" class="form-control input-sm" placeholder="Direccion" data-toggle="tooltip" title="Direccion"/>
						</div>
						<div class="form-group">
							<g:textField name="identificationCard" id="identificationCard" class="form-control input-sm" placeholder="Cedula" data-toggle="tooltip" title="Cedula"/>
						</div>
						<a href="#" id="sendClient" type="submit" class="btn btn-sm btn-primary pull-right">Agregar</a>
						<br><br>
					</div>
					<g:select name="client.id" from="${clients}" value="${client?.id}" optionKey="id" noSelection="[null:'Selecciona un cliente']" class="form-control"/>
				</div>

				<div class="form-group">
					<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${typeOfPurchase}" noSelection="[null:'Selecciona un tipo de pago']" class="form-control"/>
				</div>

				<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
			</g:form>
		</div>
		<div class="col-md-6">
			<h4>Venta general</h4>
			<g:link event="confirmGeneralSale" class="btn btn-primary">Confirmar venta general</g:link>
		</div>
	</div>
</body>
</html>