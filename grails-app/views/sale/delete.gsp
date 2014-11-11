<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
	<title>Eliminar Abonos</title>
</head>
<body>
	<div class="col-md-12">
		<div class="row">
			<div class="col-md-8">
				<h3><span class="glyphicon glyphicon-trash lock"></span> Eliminar Abonos</h3> 
			</div>
		</div>
		<hr>
		<div>
			<h4>Si procede a eliminar el abnono de: <strong>${payInstance}, de ${saleInstance},</strong> no podras deshacer los cambios! Deseas continuar?</h4>
			<g:form action="delete">
				<g:hiddenField name="id" value="${saleInstance.id}"/>
				<g:hiddenField name="idPay" value="${payInstance.id}"/>
				<g:submitButton name="delete" value="Si" class="btn btn-danger"/>
				<g:link action="pay" class="btn btn-default" params="[id:"${saleInstance.id}"]">No</g:link>
			</g:form>
		</div>
	</div>
</body>
</html>