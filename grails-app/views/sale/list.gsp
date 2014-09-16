<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<g:link action="create" class="btn btn-primary">Vender</g:link>
			</div>
		</div>
	</div>

	<g:if test="${sales}">
		<table class="table table-striped">
			<thead>
				<th></th>
			</thead>
			<tbody></tbody>
		</table>
		<g:each in="${sales}" var="sale" status="index">
			<tr>
				<td>${index + 1}</td>
			</tr>
		</g:each>
	</g:if>
	<g:else>
		<h4>Sin ventas...</h4>
	</g:else>
</body>
</html>
