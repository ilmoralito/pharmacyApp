<div class="form-group">
	<label for="name" class="sr-only">Nombre del producto</label>
	<g:textField name="name" value="${product?.name}" class="form-control" placeholder="Nombre del producto" autofocus="true"/>
</div>

<div class="form-group">
	<label for="location">Ubicacion</label>
	<g:select name="location" from="${grailsApplication.config.ni.sb.locations}" value="${product?.location}" class="form-control"/>
</div>

<g:if test="${actionName == 'createMedicine' || product instanceof ni.sb.Medicine}">
	<div class="form-group">
		<label for="code" class="sr-only">Codigo</label>
		<g:textField name="code" value="${product?.code}" class="form-control" placeholder="Codigo"/>
	</div>
	<div class="form-group">
		<label for="genericName" class="sr-only">Nombre Generico</label>
		<g:textField name="genericName" value="${product?.genericName}" class="form-control" placeholder="Nombre Generico"/>
	</div>
</g:if>

<g:if test="${actionName == 'show'}">
	<div class="form-group">
		<label for="status" class="sr-only">Estado</label>
		<g:select name="status" from="['Activo', 'Inactivo']" keys="[true, false]" value="${product?.status}" class="form-control"/>
	</div>
</g:if>