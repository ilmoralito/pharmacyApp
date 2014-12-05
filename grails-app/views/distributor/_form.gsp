<div class="form-group">
	<label for="name" class="${ actionName == 'list' ? 'sr-only' : '' }">Nombre</label>
	<g:textField name="name" value="${dealer?.name}" class="form-control" placeholder="Nombre"/>
</div>

<g:if test="${ actionName == 'list' ? 'sr-only' : '' }">
	<label>Telefonos</label>
</g:if>

<div class="form-group">
	<label for="convencional" class="${ actionName == 'list' ? 'sr-only' : '' }">Convencional</label>
	<g:textField name="convencional" value="${dealer?.telephones?.convencional}" class="form-control" placeholder="Convencional"/>
</div>
<div class="form-group">
	<label for="movistar" class="${ actionName == 'list' ? 'sr-only' : '' }">Movistar</label>
	<g:textField name="movistar" value="${dealer?.telephones?.movistar}" class="form-control" placeholder="Movistar"/>
</div>
<div class="form-group">
	<label for="claro" class="${ actionName == 'list' ? 'sr-only' : '' }">Claro</label>
	<g:textField name="claro" value="${dealer?.telephones?.claro}" class="form-control" placeholder="Claro"/>
</div>