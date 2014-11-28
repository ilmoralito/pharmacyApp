<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Inventario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, filterStock, app"/>
</head>
<body>
	<h4 id="medicine" style="cursor:pointer;">Medicinas</h4>
	<div id="medicines">
		<g:each in="${medicines}" var="medicine" status="index">
			<table class="table table-hover">
				<caption class="bg-info capStock">
					<strong>Nombre:</strong> ${medicine} - <strong>Codigo:</strong> #${medicine.code} - <strong>Ubicacion:</strong> ${medicine.location} - <strong>Laboratorio:</strong> ${medicine.provider}
				</caption>
				<colgroup>
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
				</colgroup>
				<g:if test="${index == 0}">
					<thead>
						<th>Presentacion</th>
						<th>Medida</th>
						<th>Cantidad</th>
						<th>Precio de venta</th>
						<th>Total</th>
					</thead>
				</g:if>
				<tbody>
					<g:each in="${ni.sb.MedicineOrder.findAllByProduct(medicine)}" var="m">
						<tr>
							<td>${m.presentation}</td>
							<td>${m.measure}</td>
							<td>${m.quantity}</td>
							<td>${m.sellingPrice}</td>
							<td>${m.sellingPrice * m.quantity}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</g:each>
	</div>

	<h4 id="item" style="cursor:pointer;">Productos</h4>
	<table class="table table-hover" id="items">
		<colgroup>
			<col span="1" style="width: 25%;">
			<col span="1" style="width: 25%;">
			<col span="1" style="width: 25%;">
			<col span="1" style="width: 25%;">
			<col span="1" style="width: 25%;">
		</colgroup>
		<thead>
			<th>Producto</th>
			<th>Ubicacion</th>
			<th>Cantidad</th>
			<th>Precio de venta</th>
			<th>Total</th>
		</thead>
		<tbody>
			<g:each in="${items}" var="item">
				<tr>
					<g:if test="${!(item.product.instanceOf(ni.sb.Medicine)) && !(item.product.instanceOf(ni.sb.BrandProduct))}">
						<td>${item.product}</td>
						<td>${item.product.location}</td>
						<td>${item.quantity}</td>
						<td>${item.sellingPrice}</td>
						<td>${item.quantity * item.sellingPrice}</td>
					</g:if>
				</tr>
			</g:each>
		</tbody>
	</table>

	<h4 id="brandProduct" style="cursor:pointer;">Productos de marca</h4>
	<div id="brandProducts">
		<g:each in="${brandProducts}" var="brandProduct" status="index">
			<table class="table table-hover">
				<caption class="bg-info capStock"><strong>Nombre:</strong> ${brandProduct} - <strong>Ubicacion:</strong> ${brandProduct.location}</caption>
				<colgroup>
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
					<col span="1" style="width: 20%;">
				</colgroup>
				<g:if test="${index == 0}">
					<thead>
						<th>Marca</th>
						<th>Detalle</th>
						<th>Cantidad</th>
						<th>Cantidad</th>
						<th>Cantidad</th>
					</thead>
				</g:if>
				<tbody>
					<g:each in="${ni.sb.BrandProductOrder.findAllByProduct(brandProduct)}" var="b">
						<tr>
							<td>${b.brand}</td>
							<td>${b.detail}</td>
							<td>${b.quantity}</td>
							<td>${b.sellingPrice}</td>
							<td>${b.quantity * b.sellingPrice}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</g:each>
	</div>

	<style>
		caption {
			color:#000;
		}
	</style>
</body>
</html>
