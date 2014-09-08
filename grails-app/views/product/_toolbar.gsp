<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:if test="${actionName == 'list'}">
				<g:form action="save" params="[providerId:params?.providerId]"	class="form-inline" role="form">
					<g:render template="form"/>
					<g:submitButton name="confirm" value="Agregar" class="btn btn-primary"/>
					<div class="btn-group">
						<g:link action="list" params="[providerId:provider.id]" class="${!params?.status || params?.status == 'true' ? 'active' : ''} btn btn-default">Activos</g:link>
						<g:link action="list" params="[providerId:provider.id, status:'false']" class="${params?.status == 'false' ? 'active' : ''} btn btn-default">Inactivos</g:link>
					</div>
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
				</g:form>
			</g:if>
			<g:if test="${actionName == 'show'}">
				<g:link action="list" params="[status:product.status, providerId:providerId]" class="btn btn-default pull-right">
					Regresar a lista de productos
				</g:link>
			</g:if>
		</div>
	</div>
</div>
