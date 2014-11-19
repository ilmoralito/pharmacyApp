<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Inventario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<h4>Productos</h4>
	<table class="table">
	<thead>
		<th width="1">#</th>
		<th>Nombre del producto</th>
	</thead>
	<tbody>
		<g:each in="${items}" var="item">
			<tr>
				<g:if test="${!(item.product.instanceOf(ni.sb.Medicine)) && !(item.product.instanceOf(ni.sb.BrandProduct))}">
					<td>${item.quantity}</td>
					<td>${item.product}</td>
				</g:if>
			</tr>
		</g:each>
	</tbody>
	</table>
</body>
</html>
