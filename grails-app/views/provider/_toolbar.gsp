<g:set var="target" value="${actionName == 'list' ? 'create' : 'list'}"/>

<div class="row">
	<div class="col-md-12">
		<g:link action="${target}" class="btn btn-default pull-right">
			<g:if test="${target != 'list'}">
				Crear
			</g:if>
			<g:else>
				Regresar
			</g:else>
		</g:link>
	</div>
</div>