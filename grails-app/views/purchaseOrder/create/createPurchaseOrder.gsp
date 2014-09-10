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
				<div class="form-group">
					<label for="deadline" class="sr-only">Fecha tope de pago</label>
					<g:textField name="deadline" class="form-control" placeholder="Fecha tope de pago"/>
				</div>
				<div class="form-group">
					<label for="invoiceNumber" class="sr-only">Numero de factura</label>
					<g:textField name="invoiceNumber" class="form-control" placeholder="Numero de factura"/>
				</div>
				<div class="form-group">
					<label for="typeOfPurchase" class="sr-only">Tipo de compra</label>
					<g:select name="typeOfPurchase" from="['Contado', 'Credito']" noSelection="['':'Seleccione un tipo de pago']" class="form-control"/>
				</div>
		
				<g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
</body>
</html>
