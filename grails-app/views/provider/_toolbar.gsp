<g:set var="target" value="${actionName == 'list' ? 'create' : 'list'}"/>

<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:if test="${actionName == 'list'}">
				<div class="btn-group">
					<g:link action="list" class="${!params?.status || params?.status == 'true' ? 'active' : ''} btn btn-default">
						Activos
					</g:link>
					<g:link action="list" params="[status:'false']" class="${params?.status == 'false' ? 'active' : ''} btn btn-default">
						Inactivos
					</g:link>
				</div>	
			</g:if>
			
			<g:link action="${target}" class="btn btn-primary">
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