<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Proveedor</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<div class="row">
		<div class="col-md-6">
			<h4>Proveedor</h4>
			<g:form action="update" autocomplete="off">
				<g:hiddenField name="id" value="${params?.id}"/>
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Confirmar" class="btn btn-default"/>
			</g:form>
		</div>
		<div class="col-md-6">
			<h4>Productos</h4>
			<g:form action="addProduct">
				<div class="form-group">
					<label for="product">Producto</label>
					<g:textField name="product" class="form-control"/>
				</div>
				<g:submitButton name="confirm" value="Agregar producto" class="btn btn-default"/>
			</g:form>
			<table class="table">
				<thead>
					<th></th>
					<th width="1"></th>
				</thead>
				<tbody>
					<g:each in="${products}" var="${product}" status="i">
						<tr>
							<td>${product}</td>
							<td>
								<g:link action="removeProduct" id="${i}">
									<span class="glyphicon glyphicon-trash"></span>
								</g:link>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>