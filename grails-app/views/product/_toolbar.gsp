<div class="row">
	<div class="col-md-6">
		<div class="btn-group">
			<button type="button" class="btn btn-default">${provider}</button>
		  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
		    <span class="caret"></span>
		    <span class="sr-only">${provider}</span>
		  </button>
			<ul class="dropdown-menu" role="menu">
				<g:each in="${ni.sb.Provider.list()}" var="provider">
					<li>
						<g:link action="list" params="[providerId:provider.id]">${provider}</g:link>
					</li>
				</g:each>
		  </ul>
		</div>
	</div>
	<div class="col-md-6">
		<div class="pull-right">
			<g:if test="${actionName == 'list'}">
				<div class="btn-group">
					<g:link action="list" params="[providerId:provider.id]" class="${!params?.status || params?.status == 'true' ? 'active' : ''} btn btn-default">
						Activos
					</g:link>
					<g:link action="list" params="[providerId:provider.id, status:'false']" class="${params?.status == 'false' ? 'active' : ''} btn btn-default">
						Inactivos
					</g:link>
				</div>
				<g:link action="create" class="btn btn-primary">Agregar Producto</g:link>
			</g:if>
			<g:if test="${actionName in ['show', 'create']}">
				<g:link action="list" params="[status:product.status, providerId:providerId]" class="btn btn-default pull-right">
					Regresar a lista de productos
				</g:link>
			</g:if>
		</div>
	</div>
</div>
