<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Perfil de Usuario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
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
		<g:render template="/layouts/errors" model="[instance:userInstance]"/>
		<br>
		<g:form action="updateProfile">
			<g:hiddenField name="id" value="${userInstance.id}"/>
			<div class="form-group">
				<label for="username">Correo electrónico</label>
				<g:textField type="text" name="username" maxlength="70" value="${userInstance?.username}" class="form-control" placeholder="Nombre de usuario" autofocus="true"/>
			</div>
			<div class="form-group">
				<label for="fullName">Nombre Completo</label>
				<g:textField name="fullName" class="form-control" value="${userInstance?.fullName}" placeholder="Nombre completo"/>
			</div>
			<input type="submit" class="btn btn-primary" value="Cambiar"/>
		</g:form>
	</div>
</body>
</html>
