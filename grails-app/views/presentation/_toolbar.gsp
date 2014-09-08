<div class="row">
	<div class="col-md-12">
		<div class="pull-right">
			<g:form action="save" params="[productId:params?.productId]"	class="form-inline" role="form">
				<div class="form-group">
					<label for="name" class="sr-only">Nombre</label>
					<g:select name="name" from="${presentationsList}" class="form-control"/>
				</div>
				<g:submitButton name="confirm" value="Agregar" class="btn btn-primary"/>

				<div class="btn-group">
					<button type="button" class="btn btn-default">${product}</button>
				  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				    <span class="caret"></span>
				    <span class="sr-only">${product}</span>
				  </button>
					<ul class="dropdown-menu" role="menu">
						<g:each in="${ni.sb.Product.findAllByProvider(product.provider)}" var="productInstance">
							<li>
								<g:link action="list" params="[productId:productInstance.id]">${productInstance}</g:link>
							</li>
						</g:each>
				  </ul>
				</div>
			</g:form>
		</div>
	</div>
</div>
