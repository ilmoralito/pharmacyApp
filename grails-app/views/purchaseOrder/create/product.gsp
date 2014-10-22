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

			<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px;">
			  <li><g:link event="medicine">Medicina</g:link></li>
			  <li class="active">
			  	<g:link event="product">Producto</g:link>
			  </li>
			  <li><g:link event="brand">Marca</g:link></li>
			</ul>

			<g:form autocomplete="off">
				<a href="#" id="trigger" class="btn btn-link btn-xs">Agregar producto</a>
				
				<div id="box" style="display:none;">
					<input type="hidden" name="providerId" id="providerId" value="${purchaseOrder?.provider?.id}"/>

					<div class="form-group">
						<input type="text" id="newProduct" name="newProduct" class="form-control"/>
					</div>

					<a href="#" id="send" type="submit" class="btn btn-default">Agregar</a>
				</div>

				<div class="form-group">
					<label for="product" class="sr-only">Producto</label>
					<select name="product" id="product" class="form-control">
						<g:each in="${ni.sb.Product.findAllByProviderAndStatus(purchaseOrder.provider, true)}" var="product">
							<g:if test="${!(product instanceof ni.sb.Medicine) && !(product instanceof ni.sb.BrandProduct)}">
								<option value="${product.id}">${product}</option>
							</g:if>
						</g:each>
					</select>
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
