<ul class="nav nav-tabs">
	<li class="${actionName == 'productList' ? 'active' : ''}">
		<g:link action="productList" params="[providerId:providerId]">Producto</g:link>
	</li>
	<li class="${actionName == 'medicineList' ? 'active' : ''}">
		<g:link action="medicineList" params="[providerId:providerId]">Medicamento</g:link>
	</li>
	<li class="${actionName == 'brandList' ? 'active' : ''}">
		<g:link action="brandList" params="[providerId:providerId]">Marca</g:link>
	</li>
</ul>
<br>
