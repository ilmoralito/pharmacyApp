<ul class="nav nav-tabs">
	<li class="${actionName == 'createProduct' ? 'active' : ''}">
		<g:link event="createProduct" params="[providerId:providerId]">Producto</g:link>
	</li>
	<li class="${actionName == 'createMedicine' ? 'active' : ''}">
		<g:link event="createMedicine" params="[providerId:providerId]">Medicamento</g:link>
	</li>
	<li class="${actionName == 'createBrandProduct' ? 'active' : ''}">
		<g:link event="createBrandProduct" params="[providerId:providerId]">Marcas</g:link>
	</li>
</ul>
