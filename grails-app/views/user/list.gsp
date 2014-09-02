<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Usuarios</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<div class="col-md-7">
	<g:if test="${userInstance}">
		<h4>Usuarios</h4>
		<table id="tableUsers">
			<tbody>
				<g:each in="${userInstance}" var="user">
					<g:set var="auth" value="${ni.sb.UserRole.findByUser(user)}"/>
					<tr>
						<td colspan="4"><g:link action="edit" params="[id:user.id]">${user.username}</g:link></td>
					</tr>
					<tr>
						<td>${user.fullName}</td>
						<td>${auth.role.authority}</td>
						<td>
							<g:if test="${user.enabled == true}">
								<div class="statusEnabled">ACTIVO</div>
							</g:if>
							<g:else>
								<div class="statusDisabled">INACTIVO</div>
							</g:else>
						</td>
					</tr>
					</g:each>
				</tbody>
		</table>
	</g:if>
	</div>
	<div class="col-md-5">
		<h4>Registrar Nuevo usuario</h4>
		<g:form controller="user" action="list">
			<div class="form-group">
				<g:textField type="text" class="form-control" name="username" placeHolder="Correo electrónico" required=""/>
			</div>
			<div class="form-group">
			<g:textField type="text" class="form-control" name="fullName" placeHolder="Nombre completo" required=""/>
			</div>
			<div class="form-group">
				<g:select name="authority" from="${['Administrador', 'Usuario']}" keys="['ROLE_ADMIN', 'ROLE_USER']" noSelection="[null:'Selecciona rol del usuario']" class="form-control"/>
			</div>
			<input type="submit" class="btn btn-info" value="Agregar"/>
		</g:form>
		<br>
		<g:render template="/layouts/errors" model="[instance:user]"/>
	</div>
</body>
</html>