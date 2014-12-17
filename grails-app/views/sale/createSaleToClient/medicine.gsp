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
				<g:render template="createSaleToClient/items" model="[results:results, items:medicinesToSale]"/>
			</g:if>
		</div>
	</div>
</body>
</html>