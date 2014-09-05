<div class="row">
	<div class="col-md-12">
		<g:if test="${actionName == 'list'}">
			<div class="pull-right">
				<g:form action="save" params="[providerId:params?.providerId]"	class="form-inline" role="form">
					<g:render template="form"/>
					<g:submitButton name="confirm" value="Agregar" class="btn btn-default"/>
				</g:form>
			</div>
		</g:if>
		<g:if test="${actionName == 'show'}">
			<g:link action="list" params="[providerId:providerId]" class="btn btn-default pull-right">
				Regresar a lista de productos
			</g:link>
		</g:if>
	</div>
</div>