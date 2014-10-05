<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Administrar marcas y detalles</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<g:link controller="product" action="brandList" params="[providerId:brandProduct?.provider?.id]" class="btn btn-default">
				Regresar a lista de ${brandProduct?.provider?.name}
			</g:link>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-6">
			<g:form action="update" autocomplete="off">
				<g:hiddenField name="id" value="${brandProduct?.id}"/>

				<div class="form-group">
					<label for="name" class="sr-only">Producto</label>
					<g:textField name="name" value="${brandProduct?.name}" class="form-control" placeholder="Nombre del producto"/>
				</div>

				<g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
			</g:form>
		</div>
		<div class="col-md-6">
			<g:form action="actionName">
				<div class="form-group">
					<label for="brand" class="sr-only">Marca</label>
					<input list="brands" name="brand" id="brand" class="form-control" placeholder="Marca"/>
					<datalist id="brands">
						<g:each in="${availableBrands}" var="availableBrand">
							<option value="${availableBrand}"/>
						</g:each>
					</datalist>
				</div>

				<g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
			</g:form>
			<br>

			<table class="table">
				<tbody>
					<g:each in="${brands}" var="brand">
						<tr>
							<td>${brand}</td>
							<td width="1">
								<g:link action="deleteBrand" class="pull-right">
									<span class="glyphicon glyphicon-trash"></span>
								</g:link>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<g:each in="${brand.details}" var="detail">
									<p>${detail}</p>
								</g:each>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
