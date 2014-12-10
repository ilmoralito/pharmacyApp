<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Clave de Usuario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="col-md-6">
		<g:if test="${actionName=='profile' || actionName=='updateProfile' || actionName=='password'}">
			<ul class="nav nav-tabs">
			  	<li class="${(actionName == 'profile' || actionName == 'updateProfile') ? 'active' : ''}">
			  		<g:link controller="user" action="profile">Perfil</g:link>
			  	</li>

				<li class="${(actionName == 'password' || actionName == 'updatePassword') ? 'active' : ''}">
					<g:link controller="user" action="password">
						Cambiar Contraseña
					</g:link>
				</li>
			</ul>
		</g:if>
		<br>
		<g:form action="updatePassword">
			<g:hiddenField name="id" value="${userInstance.id}"/>
			<div class="form-group">
				<g:passwordField class="form-control" name="currentPassword" placeholder="Ingrese su contraseña actual" autofocus="true" required=""/>
			</div>

			<div class="form-group">
				<g:passwordField class="form-control" name="password" placeholder="Nueva contraseña"/>
			</div>
			<div class="form-group">
				<g:passwordField class="form-control" name="confirmPassword" placeholder="Confirme su nueva contraseña" required=""/>
			</div>
			<input type="submit" class="btn btn-primary" value="Cambiar" required=""/>
		</g:form>
	</div>
</body>
</html>
