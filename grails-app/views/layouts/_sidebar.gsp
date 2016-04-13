<ul class="nav nav-pills nav-stacked">
    <sec:ifAllGranted  roles="ROLE_ADMIN">
        <li class="${controllerName == 'distributor' ? 'active' : ''}">
            <g:link controller="distributor" params="[enabled: true]">
                Distribuidores
            </g:link>
        </li>
        <li class="${controllerName in ['provider', 'product'] ? 'active' : ''}">
            <g:link controller="provider" params="[enabled: true]">
                Laboratorios
            </g:link>
        </li>
        <li class="${controllerName == 'presentation' ? 'active' : ''}">
            <g:link controller="presentation">
                Presentaciones
            </g:link>
        </li>
        <li class="${controllerName == 'brand' ? 'active' : ''}">
            <g:link controller="brand">
                Marcas
            </g:link>
        </li>
        <li class="${controllerName == 'company' ? 'active' : ''}">
            <g:link controller="company" params="[enabled: true]">
                Empresas
            </g:link>
        </li>
        <li class="${controllerName == 'purchaseOrder' && actionName != 'stock' ? 'active' : ''}">
            <g:link controller="purchaseOrder" action="list">Pedidos</g:link>
        </li>
        <li class="${controllerName == 'item' ? 'active' : ''}">
            <g:link controller="item">Inventario</g:link>
        </li>
    </sec:ifAllGranted>
    <li class="${controllerName == 'sale' ? 'active' : ''}">
        <g:link controller="sale">Ventas</g:link>
    </li>
    <li class="${controllerName == 'expense' ? 'active' : ''}">
        <g:link controller="expense">
            Diario
        </g:link>
    </li>
</ul>
