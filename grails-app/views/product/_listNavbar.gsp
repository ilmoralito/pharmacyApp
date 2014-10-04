<ul class="nav nav-tabs">
	<li class="${actionName == 'productList' ? 'active' : ''}">
		<g:link action="productList" params="[providerId:params?.providerId]">Productos</g:link>
	</li>
	<li class="${actionName == 'medicineList' ? 'active' : ''}">
		<g:link action="medicineList" params="[providerId:params?.providerId]">Medicamentos</g:link>
	</li>
	<li class="${actionName == 'brandList' ? 'active' : ''}">
		<g:link action="brandList" params="[providerId:params?.providerId]">Marcas</g:link>
	</li>
</ul>
<br>
