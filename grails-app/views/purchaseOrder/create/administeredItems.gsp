<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar articulos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tooltip, createPurchaseOrder, jquery-ui"/>
</head>
<body>
	<g:render template="create/toolbar"/>
	<br>

	<div class="row">
		<div class="col-md-9">
			<g:if test="${items}">
				<table class="table table-striped">
					<thead>
						<th>Producto</th>
						<th>Presentacion</th>
						<th>Medida</th>
						<th>Cantidad</th>
						<th>P Compra</th>
						<th>P Venta</th>
						<th>Lote</th>
						<th>Total</th>
						<th width="1"></th>
					</thead>
					<tbody>
						<g:each in="${items}" var="item" status="index">
							<tr>
								<td>${item.product.name}</td>
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
				<h4>...</h4>
			</g:else>
		</div>
		<div class="col-md-3">
			<div class="well well-sm">
				<div class="row">
					<div class="col-md-6">
						<p><b>#</b> ${purchaseOrder.invoiceNumber}</p> 
						<p>
							<span class="glyphicon glyphicon-calendar"></span>
							${purchaseOrder.deadline.format("yyyy-MM-dd")}
						</p>
						<span class="glyphicon glyphicon-th"></span>
						${purchaseOrder.typeOfPurchase}
					</div>
					<div class="col-md-6">
						<g:link event="editPurchaseOrder" class="btn btn-default btn-xs btn-block">Editar</g:link>
					</div>
				</div>
			</div>

			<g:form>
				<div class="form-group">
					<label for="product" class="sr-only">Producto</label>
					<g:select name="product" from="${ni.sb.Product.list()}" optionKey="id" optionValue="name" class="form-control"data-toggle="tooltip" data-placement="left" title="Producto"/>
				</div>
				<div class="form-group">
					<label for="presentation" class="sr-only">Presentacion</label>
					<g:select name="presentation" from="${}" class="form-control" data-toggle="tooltip" data-placement="left" title="Presentacion"/>
				</div>
				<div class="form-group">
					<label for="measure" class="sr-only">Unidad de medida</label>
					<g:select name="measure" from="${}" class="form-control" data-toggle="tooltip" data-placement="left" title="Unidad de medida"/>
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
				<div class="form-group">
					<label for="bash" class="sr-only">Lote</label>
					<g:textField name="bash" class="form-control" placeholder="Lote" data-toggle="tooltip" data-placement="left" title="Lote"/>
				</div>

				<g:submitButton name="addItem" value="Agregar producto" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
