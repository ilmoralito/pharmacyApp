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
			<g:render template="createSaleToClient/saleDetails"/>
		</div>
		<div class="col-md-4">
			<!--Client data-->
			<g:render template="createSaleToClient/clientData"/>

			<!--Balance-->
			<g:render template="createSaleToClient/balance"/>

			<!--Tabs-->
			<ul class="nav nav-tabs" role="tablist" style="margin-bottom:10px;">
			  <li><g:link event="medicine">Medicina</g:link></li>
			  <li class="active">
			  	<g:link event="manageProducts">Producto</g:link>
			  </li>
			  <li><g:link event="brand">Marca</g:link></li>
			</ul>

			

		</div>
	</div>
</body>
</html>