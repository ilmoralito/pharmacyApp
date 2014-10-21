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
		</div>
	</div>
</body>
</html>