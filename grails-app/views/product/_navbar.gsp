<ul class="nav nav-tabs">
	<li class="${actionName == 'createProduct' ? 'active' : ''}">
		<g:link action="createProduct" params="[providerId:providerId]">Productos</g:link>
	</li>
	<li class="${actionName == 'createMedicine' ? 'active' : ''}">
		<g:link action="createMedicine" params="[providerId:providerId]">Medicamentos</g:link>
	</li>
	<li class="${actionName == 'createBrandProduct' ? 'active' : ''}">
		<g:link action="createBrandProduct" params="[providerId:providerId]">Marcas</g:link>
	</li>
</ul>
<br>
