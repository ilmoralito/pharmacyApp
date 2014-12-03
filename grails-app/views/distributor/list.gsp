<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Distribuidores</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, distributor"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<table class="table">
				<thead>
					<th>Distibuidor</th>
					<th>Telefonos</th>
				</thead>
				<tbody>
					<g:each in="${dealers}" var="dealer">
						<tr>
							<td>${dealer.name}</td>
							<td>${dealer.telephones}</td>
						</tr>
					</g:each>
				</tbody>
			</table>
			
		</div>
		<div class="col-md-2">
			<g:form action="list" autocomplete="off">
				<div class="form-group">
					<label for="name" class="sr-only">Nombre</label>
					<g:textField name="name" class="form-control" placeholder="Nombre"/>
				</div>
				<div class="form-group">
					<div id="newTelephone">
						<label for="telephones" class="sr-only">Telefonos</label>
						<g:textField name="telephones" class="form-control" placeholder="Telefono"/>
					</div>
					<h6><a href="#" id="addTelephone">Agregar otro telefono</a></h6>
				</div>
				<g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
			</g:form>
		</div>
	</div>
</body>
</html>