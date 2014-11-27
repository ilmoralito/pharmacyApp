<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar articulos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tooltip, app, calculateSellingPrice, adding"/>
</head>
<body>
	<g:render template="create/toolbar"/>

	<div class="row">
		<div class="col-md-8">
			<g:render template="create/items"/>
		</div>

		<div class="col-md-4">
			<g:render template="create/purchaseOrderDetail"/>

			<ul class="nav nav-tabs" role="tablist">
			  <li><g:link event="medicine">Medicina</g:link></li>
			  <li class="active">
			  	<g:link event="product">Producto</g:link>
			  </li>
			  <li><g:link event="brand">Marca</g:link></li>
			</ul>

			<g:form autocomplete="off">
				<div class="row">
					<div class="col-md-12">
						<a href="#" id="trigger" class="btn btn-link btn-xs pull-right">
							Agregar nuevo producto
						</a>
					</div>
				</div>
				<div id="box">
					<div class="form-group">
						<label class="sr-only">Proveedor</label>
						<g:select name="providerId" from="${providers}" optionKey="id" noSelection="[null:'Selecciona un proveedor']" class="form-control input-sm"/>
					</div>
					<div class="form-group">
						<input type="text" id="newProduct" name="newProduct" class="form-control input-sm" placeholder="Nombre del producto" data-toggle="tooltip" title="Nombre del producto"/>
					</div>
					<a href="#" id="sendProduct" type="submit" class="btn btn-sm btn-primary pull-right">Agregar</a>
					<br><br>
				</div>

				<div class="form-group">
					<label for="product" class="sr-only">Producto</label>
					<g:select name="product" from="${ni.sb.Product.findAllByProviderInListAndStatus(purchaseOrder.providers, true).findAll {!(it.instanceOf(ni.sb.Medicine)) && !(it.instanceOf(ni.sb.BrandProduct))}}" optionKey="id" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="quantity" class="sr-only">Cantidad</label>
					<g:textField name="quantity" class="form-control" placeholder="Cantidad" data-toggle="tooltip" data-placement="left" title="Cantidad"/>
				</div>
				<div class="form-group">
					<label for="purchasePrice" class="sr-only">Precio de compra</label>
					<g:textField name="purchasePrice" class="form-control" placeholder="Precio de compra" data-toggle="tooltip" data-placement="left" title="Precio de compra"/>
				</div>
				<div class="form-group">
					<label for="sellingPrice" class="sr-only">Precio de venta</label>
					<g:textField name="sellingPrice" class="form-control" placeholder="Precio de venta" data-toggle="tooltip" data-placement="left" title="Precio de venta"/>
				</div>

				<g:submitButton name="addItem" value="Agregar producto" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
