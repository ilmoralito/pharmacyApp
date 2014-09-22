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
		<div class="col-md-9">
			<g:if test="${itemInstance}">
				<table class="table table-striped">
					<thead>
						<th>Producto</th>
						<th>Presentacion</th>
						<th>Medida</th>
						<th>Cantidad</th>
						<th>P Compra</th>
						<th>P Venta</th>
						<th>Vencimiento</th>
						<th>Total</th>
						<th width="1"></th>
					</thead>
					<tbody>
						<g:each in="${itemInstance}" var="item" status="index">
							<tr>
								<td><g:link action="editItem" id="${item.id}">${item.product.name}</g:link></td>
								<td>${item.presentation}</td>
								<td>${item.measure}</td>
								<td>${item.quantity}</td>
								<td>${item.purchasePrice}</td>
								<td>${item.sellingPrice}</td>
								<td>${item.bash}</td>
								<td>${item.total}</td>
								<td>
									<g:link event="deleteItem" params="[product:item.product.id, presentation:item.presentation.id, measure:item.measure, bash:item.bash]">
										<span class="glyphicon glyphicon-trash"></span>
									</g:link>
								</td>
							</tr>
						</g:each>
						<tr>
							<td>BALANCE</td>
							<td colspan="7">
								<div class="pull-right">${purchaseOrder?.balance ?: 0}</div>
							</td>
							<td width="1"></td>
						</tr>
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
					<div class="col-md-12">
						<g:link action="show" id="${purchaseOrder?.id}" class="btn btn-xs btn-primary btn-block">Editar #${item?.purchaceOrder?.invoiceNumber}</g:link>
					</div>
				</div>

				<div class="row" style="margin-top:10px;">
					<div class="col-md-12">
						<span class="glyphicon glyphicon-calendar"></span>
						${purchaseOrder?.dutyDate?.format("yyyy-MM-dd")}
						<br>
						<span class="glyphicon glyphicon-th"></span>
						${purchaseOrder?.typeOfPurchase}
					</div>
				</div>
			</div>

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
