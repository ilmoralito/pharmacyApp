<div class="col-md-6">
	<h3>Datos Generales</h3>
	<g:form action="updateProfile">
		<g:hiddenField name="id" value="${userInstance.id}"/>
		<div class="form-group">
			<label for="username">Correo electrónico</label>
			<g:textField type="text" name="username" maxlength="70" value="${userInstance?.username}" class="form-control" placeholder="Nombre de usuario" autofocus="true"/>
		</div>
		<div class="form-group">
			<label for="username">Nombre Completo</label>
			<g:textField name="fullName" class="form-control" value="${userInstance?.fullName}" placeholder="Nombre completo"/>
		</div>
		<input type="submit" class="btn btn-info" value="Cambiar"/>
	</g:form>
</div>
