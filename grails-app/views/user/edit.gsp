<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Actualizar Usuario</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<h4>Actualizar usuario</h4>
	<hr>
	<div class="row">
		<div class="col-md-5">
			<g:set var="auth" value="${ni.sb.UserRole.findByUser(userInstance)}"/>
			<g:form controller="user" action="edit">
				<g:hiddenField name="id" value="${userInstance.id}"/>
				<g:hiddenField name="email" value=""/>
				<div class="form-group">
				    <g:textField type="text" class="form-control" name="username" placeHolder="correo electrónico" required="" value="${userInstance?.username}"/>
				</div>
				<div class="form-group">
				    <g:textField type="text" class="form-control" name="fullName" placeHolder="Nombre completo" required="" value="${userInstance?.fullName}"/>
				</div>
				<div class="form-group">
					<g:select from="${ni.sb.Role.list().authority}" name="authority" value="${auth.role.authority}" class="form-control"/>
				</div>
				<input type="submit" class="btn btn-info" value="Actualizar"/>
				<g:link controller="user" action="list" class="btn btn-default">Regresar</g:link>
			</g:form>
		</div>
		<div class="col-md-3">
			<h4>Cuenta de usuario</h4>
			<g:if test="${userInstance.enabled == true}">
				<div class="statusEnabled" align="center">ACTIVA</div>
			</g:if>
			<g:else>
				<div class="statusDisabled" align="center">INACTIVA</div>
			</g:else>
			<br>
			<div class="btn-group">
			  	<g:link controller="user" action="enabled" params="[id:userInstance.id, enabled:true]" class="btn btn-default">Habilitar</g:link>
				<g:link controller="user" action="enabled" params="[id:userInstance.id, enabled:false]" class="btn btn-default">Deshabilitar</g:link>
			</div>
		</div>
	</div>
	<div class="row">
		<g:render template="/layouts/errors" model="[instance:userInstance]"/>
	</div>
</body>
</html>