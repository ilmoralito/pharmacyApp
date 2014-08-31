<g:set var="target" value="${actionName == 'list' ? 'create' : 'list'}"/>

<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:if test="${actionName == 'show'}">
				<g:link action="delete" id="${provider?.id}" class="btn btn-default">
					<span class="glyphicon glyphicon-trash"></span>
				</g:link>
			</g:if>
			<g:link action="${target}" class="btn btn-default">
				<g:if test="${target != 'list'}">Crear</g:if>
				<g:else>Regresar</g:else>
			</g:link>
		</div>
	</div>
</div>