<ul class="nav nav-pills nav-stacked">
    <li class="${controllerName == 'distributor' ? 'active' : ''}">
        <g:link controller="distributor" action="list">Distribuidores</g:link>
    </li>
    <li class="${controllerName in ['provider', 'product', 'presentation', 'brand'] ? 'active' : ''}">
        <g:link controller="provider" action="list" params="[status: true]">Proveedores</g:link>
    </li>
    <li class="${controllerName == 'client' ? 'active' : ''}">
        <g:link controller="client" action="list">Clientes</g:link>
    </li>
    <li class="${controllerName == 'purchaseOrder' && actionName != 'stock' ? 'active' : ''}">
        <g:link controller="purchaseOrder" action="list">Pedidos</g:link>
    </li>
    <li class="${controllerName == 'sale' ? 'active' : ''}">
        <g:link controller="sale" action="list">Ventas</g:link>
    </li>
    <li class="${controllerName == 'expense' ? 'active' : ''}">
        <g:link controller="expense">
            Diario
        </g:link>
    </li>
    <li class="${controllerName == 'purchaseOrder' && actionName == 'stock' ? 'active' : ''}">
        <g:link controller="purchaseOrder" action="stock">Inventario</g:link>
    </li>
    <li class="${controllerName == 'reports' ? 'active' : ''}">
        <g:link controller="reports" action="sales">Reportes</g:link>
    </li>
</ul>
