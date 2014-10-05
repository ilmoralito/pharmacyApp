<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<div class="btn-group">
				<g:link action="${actionName}" params="[providerId:provider.id]" class="${!params?.status || params?.status == 'true' ? 'active' : ''} btn btn-default">
					Activos
				</g:link>
				<g:link action="${actionName}" params="[providerId:provider.id, status:'false']" class="${params?.status == 'false' ? 'active' : ''} btn btn-default">
					Inactivos
				</g:link>
			</div>
			<g:set var="fixedActionName" value="${actionName.replaceAll('List', '').capitalize()}"/>
			
			<g:link action="create${ actionName == 'brandList' ? fixedActionName + 'Product' : fixedActionName}" params="[providerId:params?.providerId]" class="btn btn-default">
				Crear
			</g:link>
		</div>
	</div>
</div>