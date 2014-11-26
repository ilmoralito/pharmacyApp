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
			  <li class="active">
			  	<g:link event="medicine">Medicina</g:link>
			  </li>
			  <li><g:link event="manageProducts">Producto</g:link></li>
			  <li><g:link event="manageBrands">Marca</g:link></li>
			</ul>

			<!--Filter medicines-->
			<g:form>
				<div class="form-group">
					<g:select name="genericName" from="${genericNames}" value="${genericName}" noSelection="['':'Nombres genericos']" class="form-control input-sm"/>
				</div>

				<div class="form-group">
					<g:select name="product" from="${medicinesFiltredByGenericName ?: medicines}" optionKey="name" value="${product}" class="form-control input-sm"/>
				</div>

				<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block btn-sm"/>
			</g:form>

			<g:if test="${results}">
				<g:each in="${results.keySet()}" var="presentation">
					<h4>${presentation}</h4>

					<g:each in="${results[presentation]}" var="medicine">
						<table class="table" style="margin:0;">
							<tbody>
								<tr style="font-size:0.85em;">
									<g:form>
										<g:hiddenField name="id" value="${medicine.id}"/>

										<td style="vertical-align: middle;">
											${medicine.measure}
										</td>
										<td style="vertical-align: middle;">
											<g:formatDate date="${medicine.bash}" formatName="fv.date.format"/>
										</td>
										<td style="vertical-align: middle;">
											${medicine.sellingPrice}
										</td>
										<td style="vertical-align: middle;">
											${medicine.product.location}
										</td>
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