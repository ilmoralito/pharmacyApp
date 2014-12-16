<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar venta</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-8">
			<g:render template="saleDetail"/>
		</div>
		<div class="col-md-4">
			<!--Client data-->
			<g:render template="createSaleToClient/clientData"/>

			<!--Balance-->
			<div class="well well-sm">
				Saldo: <pharmacyApp:getSaleBalance medicinesToSale="${medicinesToSale}" productsToSale="${productsToSale}" brandsToSale="${brandsToSale}"/>
			</div>

			<!--Tabs-->
			<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px;">
			  <li><g:link event="medicine">Medicina</g:link></li>
			  <li><g:link event="manageProducts">Producto</g:link></li>
			  <li class="active">
			  	<g:link event="manageBrands">Marca</g:link>
			  </li>
			</ul>

			<!--Brands-->
			<g:form>
				<div class="form-group">
					<g:select name="product" from="${brandProductOrders}" optionKey="name" value="${product}" class="form-control input-sm"/>
				</div>

				<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block btn-sm"/>
			</g:form>
			<br>

			<!--Filter results-->
			<g:if test="${brandProductOrderByBrand}">
				<table class="table table-hover">
					<tbody>
						<g:each in="${brandProductOrderByBrand}" var="brand">
							<tr>
								<td colspan="5"><strong>${brand.key}</strong></td>
							</tr>
							<g:each in="${brand.value}" var="item">
								<tr>
									<td colspan="5">${item.key}</td>
								</tr>
								<g:each in="${item.value}" var="b">
									<g:form>
										<g:hiddenField name="id" value="${b.id}"/>

										<tr style="font-size:0.85em;">
											<td>${b.detail}</td>
											<td>${b.sellingPrice}</td>
											<td>${b.product.location}</td>
											<td>
												<g:set var="q" value="${brandsToSale.find { it.item == b }?.quantity ?: 0}"/>
												<g:set var="quantity" value="${brandsToSale ? b.quantity - q : b.quantity}"/>

												<input type="number" name="quantity" min="1" max="${quantity}" required class="form-control input-sm" placeholder="${quantity}"/>
											</td>
											<td>
												<g:submitButton name="addItem" value="Ir" class="btn btn-primary btn-xs"/>
											</td>
										</tr>
									</g:form>
								</g:each>
							</g:each>
						</g:each>
					</tbody>
				</table>
			</g:if>
		</div>
	</div>

	
</body>
</html>