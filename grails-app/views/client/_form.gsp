<div class="form-group">
	<label for="fullName" class="sr-only">Nombre completo</label>
	<g:textField name="fullName" value="${client?.fullName}" class="form-control" placeholder="Nombre completo" autofocus="true"/>
</div>
<div class="form-group">
	<label for="address" class="sr-only">Direccion</label>
	<g:textField name="address" value="${client?.address}" class="form-control" placeholder="Direccion"/>
</div>
<div class="form-group">
	<label for="identificationCard" class="sr-only">Cedula</label>
	<g:textField name="identificationCard" value="${client?.identificationCard}" class="form-control" placeholder="Cedula"/>
</div>
<g:if test="${actionName == 'show'}">
	<div class="form-group">
		<label for="status" class="sr-only">Estado</label>
		<g:select name="status" from="['Activo', 'Inactivo']" keys="[true, false]" value="${client?.status}" class="form-control"/>
	</div>
</g:if>