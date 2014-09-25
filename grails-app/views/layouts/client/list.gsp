<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><g:layoutTitle default="Farmacia Santa Barbara"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<r:layoutResources/>
</head>
<body>
	<!--NAVBAR-->
	<g:render template="/layouts/navbar"/>
	<div class="container">
		<!--SIDEBAR-->
		<div class="col-md-2">
			<g:render template="/layouts/sidebar"/>
		</div>
		<!--MAIN-->
		<div class="col-md-8">
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
									${client.fullName}</g:link>
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
		<div class="col-md-2">
			<g:layoutBody/>
			<g:if test="${flash.message}">
				<small><br>${flash.message}</small>
			</g:if>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>