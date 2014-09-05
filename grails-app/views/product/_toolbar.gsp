<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:form action="save" params="[providerId:params?.providerId]"	class="form-inline" role="form">
					<div class="form-group">
						<label for="code" class="sr-only">Codigo</label>
						<g:textField name="code" class="form-control" placeholder="Codigo"/>
					</div>
					<div class="form-group">
						<label for="name" class="sr-only">Nombre del producto</label>
						<g:textField name="name" class="form-control" placeholder="Nombre del producto"/>
					</div>
					<g:submitButton name="confirm" value="Agregar" class="btn btn-default"/>
			</g:form>
		</div>
	</div>
</div>