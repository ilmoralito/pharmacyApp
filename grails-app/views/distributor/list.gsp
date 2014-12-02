<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Distribuidores</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
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
			
		</div>
	</div>
</body>
</html>