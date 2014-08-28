<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Perfil de Usuario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<g:render template="/layouts/tabs"/>
			<g:render template="/layouts/errors"/>
			<g:render template="profileForm"/>
		</div>
	</div>
</body>
</html>
