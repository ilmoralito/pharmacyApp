<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar articulos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, createPurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>
	<br>
	<div class="row">
		<div class="col-md-3">
			<g:form autocomplete="off">
				<div class="form-group">
					<label for="product" class="sr-only">Producto</label>
					<g:select name="product" from="${ni.sb.Product.list()}" optionKey="id" optionValue="name" class="form-control"/>
				</div>

				<div class="form-group">
					<label for="presentation" class="sr-only">Presentacion</label>
					<g:select name="presentation" from="" class="form-control"/>
				</div>

				<div class="form-group">
					<label for="measure" class="sr-only">Unidad de medida</label>
					<g:select name="measure" from="" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="quantity" class="sr-only">Cantidad</label>
					<g:textField name="quantity" class="form-control" placeholder="Cantidad"/>
				</div>
				<div class="form-group">
					<label for="purchasePrice" class="sr-only">Precio de compra</label>
					<g:textField name="purchasePrice" class="form-control" placeholder="Precio de compra"/>
				</div>
				<div class="form-group">
					<label for="sellingPrice" class="sr-only">Precio de venta</label>
					<g:textField name="sellingPrice" class="form-control" placeholder="Precio de venta"/>
				</div>
				<div class="form-group">
					<label for="bash" class="sr-only">Lote</label>
					<g:textField name="bash" class="form-control" placeholder="Lote"/>
				</div>

				<g:submitButton name="addItem" value="Agregar producto" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
