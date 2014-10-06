<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Crear marca y detalles</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="createBrandProduct/nav"/>
	<br>

	<div class="row">
		<div class="col-md-6">
			<g:form autocomplete="off">
				<div class="form-group">
					<label for="brand" class="sr-only">Marca</label>
					<input list="brands" name="brand" id="brand" class="form-control" placeholder="Marca" autofocus="true"/>
					<datalist id="brands">
						<g:each in="${availableBrands ?: brands}" var="availableBrand">
							<option value="${availableBrand}"/>
						</g:each>
					</datalist>
				</div>
			
				<div class="form-group">
					<label for="details" class="sr-only"></label>
					<g:textField name="details" class="form-control" placeholder="Detalle(s) Ej. Detalle o Detalle1, Detalle2"/>
				</div>
			
				<g:submitButton name="add" value="Agregar producto" class="btn btn-primary"/>
			</g:form>
		</div>
		<div class="col-md-6">
			<g:if test="${brandProductBrands}">
				<table class="table" style="margin: 5px 0 5px 0;">
					<tbody>
						<g:each in="${brandProductBrands}" var="brand" status="index">
							<tr>
								<td>${brand}</td>
								<td width="1">
									<g:link event="delete" id="${brand}" class="pull-right">
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

				<g:link event="confirm" class="btn btn-primary">Confirmar proceso</g:link>
			</g:if>
		</div>
	</div>
</body>
</html>
