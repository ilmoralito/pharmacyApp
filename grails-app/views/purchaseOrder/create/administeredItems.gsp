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

	<div class="row">
		<div class="col-md-4">
			Numero de factura: ${purchaseOrder.invoiceNumber}
		</div>
		<div class="col-md-4">
			Fecha maxima de pago: ${purchaseOrder.deadline.format("yyyy-MM-dd")}
		</div>
		<div class="col-md-4">
			Tipo de compra: ${purchaseOrder.typeOfPurchase}
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-9">
			<g:if test="${items}">
				<h4>Productos</h4>
				<table class="table table-striped">
					<thead>
						<th>Producto</th>
						<th>Presentacion</th>
						<th>Medida</th>
						<th>Cantidad</th>
						<th>Precio de compra</th>
						<th>Precio de venta</th>
						<th>Lote</th>
						<th>Total</th>
						<th width="1"></th>
					</thead>
					<tbody>
						<g:each in="${items}" var="item" status="index">
							<tr>
								<td>${item.product}</td>
								<td>${item.presentation}</td>
								<td>${item.measure}</td>
								<td>${item.quantity}</td>
								<td>${item.purchasePrice}</td>
								<td>${item.sellingPrice}</td>
								<td>${item.bash}</td>
								<td>${item.total}</td>
								<td>
									<g:link event="deleteItem" params="[index:index]">
										<span class="glyphicon glyphicon-trash"></span>
									</g:link>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h4>Agrega productos...</h4>
			</g:else>
		</div>
		<div class="col-md-3">
			<h4>Agregar producto</h4>
			<g:form>
				<div class="form-group">
					<label for="product" class="sr-only">Producto</label>
					<g:select name="product" from="${ni.sb.Product.list()}" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="presentation" class="sr-only">Presentacion</label>
					<g:select name="product" from="${}" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="measure" class="sr-only">Unidad de medida</label>
					<g:select name="measure" from="${}" class="form-control"/>
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
