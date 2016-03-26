<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Inventario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, filterStock, app"/>
</head>
<body>
	<h4 id="medicine" style="cursor:pointer; margin-bottom:0;">Medicinas</h4>
	<div id="medicines">
		<div class="row">
			<div class="col-md-12">
				<div class="btn-group pull-right">
					<g:link action="stock" class="btn btn-sm btn-default ${!params?.reportTypeMedicine ? 'active' : ''}">
						Agrupados por proveeder
					</g:link>
					<g:link action="stock" params="[reportTypeMedicine:true]" class="btn btn-sm btn-default ${params?.reportTypeMedicine ? 'active' : ''}">
						Sin agrupar
					</g:link>
				</div>
			</div>
		</div>

		<g:if test="${!params?.reportTypeMedicine}">
			<g:each in="${medicines}" var="medicine">
				<g:each in="${medicine}" var="collection">
					<h5>${collection.key}</h5>
					<g:each in="${collection.value}" var="presentation">
						<h5>${presentation.key}</h5>
						<g:render template="medicinesInStock" model="[list:presentation.value]"/>
					</g:each>
				</g:each>
			</g:each>
		</g:if>
		<g:else>
			<g:each in="${medicines}" var="m">
				<h5>${m.key}</h5>
				<g:render template="medicinesInStock" model="[list:m.value]"/>
			</g:each>
		</g:else>
	</div>

	<h4 id="item" style="cursor:pointer;">Productos</h4>
	<table class="table table-hover" id="items">
		<colgroup>
			<col span="1" style="width: 20%;">
			<col span="1" style="width: 20%;">
			<col span="1" style="width: 20%;">
			<col span="1" style="width: 20%;">
			<col span="1" style="width: 20%;">
		</colgroup>
		<thead>
			<th>Producto</th>
			<th>Ubicacion</th>
			<th>Cantidad</th>
			<th>Precio de venta</th>
			<th>Monto total</th>
		</thead>
		<tbody>
			<g:each in="${items}" var="item">
				<tr>
					<td>${item.product}</td>
					<td>${item.quantity}</td>
					<td>${item.sellingPrice}</td>
					<td>${item.total}</td>
				</tr>
			</g:each>
			<tr>
				<td colspan="4">Monto en productos</td>
				<td colspan="1">${items.total.sum()}</td>
			</tr>
		</tbody>
	</table>

	<h4 id="brandProduct" style="cursor:pointer;">Productos de marca</h4>
	<div id="brandProducts">
		<g:each in="${brandProducts}" var="brandProduct">
			<h5><strong>${brandProduct.key}</strong></h5>
			<g:each in="${brandProduct.value}" var="detail">
				<h5>${detail.key}</h5>
				<table class="table table-hover">
					<colgroup>
						<col span="1" style="width: 20%;">
						<col span="1" style="width: 20%;">
						<col span="1" style="width: 20%;">
						<col span="1" style="width: 20%;">
						<col span="1" style="width: 20%;">
					</colgroup>
					<thead>
						<th>Nombre</th>
						<th>Ubicacion</th>
						<th>Cantidad</th>
						<th>Precio de venta</th>
						<th>Total</th>
					</thead>
					<tbody>
						<g:each in="${detail.value}" var="b">
							<tr>
								<td>${b}</td>
								<td>${b.quantity}</td>
								<td>${b.sellingPrice}</td>
								<td>${b.sellingPrice * b.quantity}</td>
							</tr>
						</g:each>
						<tr>
							<td colspan="4"></td>
							<td>${detail.value.collect { it.sellingPrice * it.quantity }.sum()}</td>
						</tr>
					</tbody>
				</table>
			</g:each>
		</g:each>
	</div>
</body>
</html>
