<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Seleccionar cliente</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<h4>Selecciona cliente y tipo de pago</h4>

			<g:form>
				<div class="form-group">
					<label for="client">Cliente</label>
					<g:select name="client.id" from="${clients}" value="${client}" optionKey="id" class="form-control"/>
				</div>

				<div class="form-group">
					<label for="typeOfPurchase">Tipo de compra</label>
					<g:select name="typeOfPurchase" from="['Contado', 'Credito']" value="${typeOfPurchase}" class="form-control"/>
				</div>

				<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
				<g:link event="cancel" class="btn btn-default">Cancelar</g:link>
			</g:form>
		</div>
	</div>
</body>
</html>