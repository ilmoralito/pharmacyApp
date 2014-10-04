<div class="row">
	<div class="col-md-12">
		<div class="btn-group pull-right">
			<g:link action="${actionName}" params="[providerId:provider.id]" class="${!params?.status || params?.status == 'true' ? 'active' : ''} btn btn-default">
				Activos
			</g:link>
			<g:link action="${actionName}" params="[providerId:provider.id, status:'false']" class="${params?.status == 'false' ? 'active' : ''} btn btn-default">
				Inactivos
			</g:link>
		</div>
	</div>
</div>