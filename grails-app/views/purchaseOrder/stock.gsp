<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Existencias</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<h4>Medicinas</h4>
	<table class="table">
		<tbody>
			<g:each in="${items}" var="item">
				<tr>
					<g:if test="${item.product.instanceOf(ni.sb.Medicine)}">
						<td>${item}</td>
					</g:if>
				</tr>
			</g:each>
		</tbody>
	</table>

	<h4>Productos</h4>
	<table class="table">
	<tbody>
		<g:each in="${items}" var="item">
			<tr>
				<g:if test="${!(item.product.instanceOf(ni.sb.Medicine)) && !(item.product.instanceOf(ni.sb.BrandProduct))}">
					<td>${item}</td>
				</g:if>
			</tr>
		</g:each>
	</tbody>
	</table>

	<h4>Productos</h4>
	<table class="table">
		<tbody>
			<g:each in="${items}" var="item">
				<tr>
					<g:if test="${item.product.instanceOf(ni.sb.BrandProduct)}">
						<td>${item}</td>
					</g:if>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
