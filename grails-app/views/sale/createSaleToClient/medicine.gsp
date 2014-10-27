<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar venta</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, saleMedicine"/>
</head>
<body>
	<div class="row">
		<div class="col-md-8">
			<!--Medicines-->
			<g:if test="${medicinesToSale}">
				<h4>Medicinas</h4>

				<table class="table">
					<thead>
						<th>Nombre</th>
						<th>Presentacion</th>
						<th>Medida</th>
						<th>Cantidad</th>
						<th>Total a pagar</th>
						<th width="1"></th>
					</thead>
					<tbody>
						<g:each in="${medicinesToSale}" var="medicine">
							<tr>
								<td>${medicine.item.product.name}</td>
								<td>${medicine.item.presentation}</td>
								<td>${medicine.item.measure}</td>
								<td>${medicine.quantity}</td>
								<td>${medicine.total}</td>
								<td><g:link event="deleteItem"><span class="glyphicon glyphicon-trash"></span></g:link></td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>

		</div>
		<div class="col-md-4">
			<!--Datos de cliente-->
			<table class="table" style="margin-bottom:10px;">
				<tbody>
					<tr>
						<td style="border:0;">Cliente</td>
						<td style="border:0;">${client}</td>
					</tr>
					<tr>
						<td>Tipo de compra</td>
						<td>${typeOfPurchase}</td>
					</tr>
				</tbody>
			</table>
			<g:link event="selectCustomer" class="btn btn-default btn-block">Cambiar datos</g:link>
			<br>

			<!--Tabs-->
			<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px;">
			  <li class="active">
			  	<g:link event="medicine">Medicina</g:link>
			  </li>
			  <li><g:link event="product">Producto</g:link></li>
			  <li><g:link event="brand">Marca</g:link></li>
			</ul>

			<!--Filter medicines-->
			<g:form>
				<div class="form-group">
					<g:select name="genericName" from="${genericNames}" value="${genericName}" noSelection="['':'Nombres genericos']" class="form-control"/>
				</div>

				<div class="form-group">
					<g:select name="product" from="${medicinesFiltredByGenericName ?: medicines}" optionKey="name" value="${product}" class="form-control"/>
				</div>

				<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>

			<g:if test="${results}">
				<g:each in="${results.keySet()}" var="presentation">
					<h4>${presentation}</h4>

					<g:each in="${results[presentation]}" var="medicine">
						<table class="table" style="margin:0;">
							<tbody>
								<tr>
									<g:form>
										<g:hiddenField name="id" value="${medicine.id}"/>

										<td style="vertical-align: middle;">${medicine.measure}</td>
										<td style="vertical-align: middle;"><g:formatDate date="${medicine.bash}" formatName="fv.date.format"/></td>
										<td style="vertical-align: middle;">${medicine.sellingPrice}</td>
										<td style="vertical-align: middle;">
											<g:set var="q" value="${medicinesToSale.find { it.item == medicine }?.quantity ?: 0}"/>
											<g:set var="quantity" value="${medicinesToSale ? medicine.quantity - q : medicine.quantity}"/>

											<input type="number" name="quantity" id="quantity" min="1" max="${quantity}" required x-moz-errormessage="Requerido" class="form-control input-sm" placeholder="${quantity}"/>
										</td>
										<td style="vertical-align: middle;">
											<g:submitButton name="addItem" value="Ir" class="btn btn-primary btn-xs"/>
										</td>
									</g:form>
								</tr>
							</tbody>
						</table>
					</g:each>
				</g:each>
			</g:if>
		</div>
	</div>
</body>
</html>