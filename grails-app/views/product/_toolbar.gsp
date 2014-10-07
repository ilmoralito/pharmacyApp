<div class="row">
	<div class="col-md-12">
		<g:if test="${actionName in ['show', 'createProduct', 'createMedicine', 'createBrandProduct']}">
			<!--TODO:I am not proud of this code but right now i need to move forward-->
			<g:link action="${ actionName == 'createProduct' ? 'productList' : actionName == 'createMedicine' ? 'medicineList' : actionName in ['createBrandProduct', 'brandsAndDetails'] ? 'brandList' : 'productList' }" params="[status:product?.status, providerId: provider.id ?: providerId]" class="btn btn-default">
				Regresar a lista de productos
			</g:link>
		</g:if>
		<g:else>
			<div class="btn-group">
				<button type="button" class="btn btn-default">${provider}</button>
			  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			    <span class="caret"></span>
			    <span class="sr-only">${provider}</span>
			  </button>
				<ul class="dropdown-menu" role="menu">
					<g:each in="${ni.sb.Provider.list()}" var="providerInstance">
						<li class="${provider == providerInstance ? 'active' : ''}">
							<g:link action="${actionName}" params="[providerId:providerInstance.id]">${providerInstance}</g:link>
						</li>
					</g:each>
			  </ul>
			</div>
		</g:else>
	</div>
</div>
<br>
