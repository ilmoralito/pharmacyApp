<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Venta</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:render template="saleDetail"/>
		</div>
		<div class="col-md-2">
			<h4>Fecha de venta</h4>
			<g:formatDate date="${sale.dateCreated}" formatName="custom.date.format"/>

			<h4>Vendedor</h4>
			${sale.user}

			<h4>Saldo de venta</h4>
			${sale.balance}

			<g:if test="${sale.instanceOf(ni.sb.SaleToClient)}">
				<h4>Cliente</h4>
				${sale.client}

				<h4>Tipo de compra</h4>
				${sale.typeOfPurchase}

				<h4>Estado</h4>
				${sale.status}
			</g:if>
		</div>
	</div>
</body>
</html>
