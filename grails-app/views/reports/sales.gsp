<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			
		</div>
		<div class="col-md-2">
			<h4>Filtrar</h4>
			<g:form action="sales">
				<div class="form-group">
					<label for="from" class="sr-only">Desde</label>
					<g:textField name="from" value="${params?.from}" class="form-control" placeholder="Desde..."/>
				</div>

				<div class="form-group">
					<label for="to" class="sr-only">Hasta</label>
					<g:textField name="to" value="${params?.to}" class="form-control" placeholder="Hasta..."/>
				</div>

				<g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
