<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Clientes</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-9">
			<h4>Clientes</h4>
			<g:if test="${clients}">
				<table class="table table-striped">
					<thead>
						<th>Nombre</th>
						<th>Direccion</th>
					</thead>
					<tbody>
						<g:each in="${clients}" var="client">
							<tr>
								<td><g:link action="show" id="${client.id}">
									${client.fullName} <small>${client.identificationCard}</small></g:link>
								</td>
								<td>${client.address}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<!--TODO: Add something more usefull here-->
				<h1>...</h1>
			</g:else>
		</div>
		<div class="col-md-3">
			<h4>Crear cliente</h4>
			<g:form action="save" autocomplete="off">
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Agregar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>