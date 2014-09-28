<ul class="nav nav-pills nav-stacked">
	<li class="${controllerName in ['provider', 'product', 'presentation'] ? 'active' : ''}">
		<g:link controller="provider" action="list">Proveedores</g:link>
	</li>
	<li class="${controllerName == 'client' ? 'active' : ''}">
		<g:link controller="client" action="list">Clientes</g:link>
	</li>
	<li class="${controllerName == 'purchaseOrder' ? 'active' : ''}">
		<g:link controller="purchaseOrder" params="[option:"true"]" action="list">Pedidos</g:link>
	</li>
	<li class="${controllerName == 'sale' ? 'active' : ''}">
		<g:link controller="sale" action="list">Ventas</g:link>
	</li>
</ul>