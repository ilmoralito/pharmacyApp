<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:form action="save"	class="form-inline" role="form">
					<div class="form-group">
						<label for="code" class="sr-only">Codigo</label>
						<g:textField name="code" class="form-control" placeholder="Codigo"/>
					</div>
					<div class="form-group">
						<label for="node" class="sr-only">Nombre del producto</label>
						<g:textField name="node" class="form-control" placeholder="Nombre del producto"/>
					</div>
					<g:submitButton name="confirm" value="Agregar" class="btn btn-default"/>
			</g:form>
		</div>
	</div>
</div>