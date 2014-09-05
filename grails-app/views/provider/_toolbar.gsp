<g:set var="target" value="${actionName == 'list' ? 'create' : 'list'}"/>

<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:if test="${actionName == 'show'}">
				<g:link action="delete" params="[id:provider.id]" class="btn btn-default">Borrar proveedor</g:link>
			</g:if>
			<g:link action="${target}" class="btn btn-default">
				<g:if test="${target != 'list'}">
					Crear proveedor
				</g:if>
				<g:else>
					Listar proveedores
				</g:else>
			</g:link>
		</div>
	</div>
</div>