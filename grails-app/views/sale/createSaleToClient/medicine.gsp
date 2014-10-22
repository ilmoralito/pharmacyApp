<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar venta</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, filterMedicinesByGenericName"/>
</head>
<body>
	<div class="row">
		<div class="col-md-8">
			
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

			<!--Medicines-->
			<div class="form-group">
				<g:select name="grenericName" from="${genericNames}" noSelection="['':'Nombres genericos']" class="form-control"/>
			</div>

			<g:form>
				<div class="form-group">
					<g:select name="product" from="${medicines}" optionKey="id" class="form-control"/>
				</div>
				<g:submitButton name="search" value="Filtrar" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
</body>
</html>