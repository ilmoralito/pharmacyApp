<ul class="nav nav-pills nav-stacked">
    <sec:ifAllGranted  roles="ROLE_ADMIN">
        <li class="${controllerName == 'distributor' ? 'active' : ''}">
            <g:link controller="distributor">
                Distribuidores
            </g:link>
        </li>
        <li class="${controllerName in ['provider', 'product'] ? 'active' : ''}">
            <g:link controller="provider">
                Laboratorios ${provider?.name}
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
            <g:link controller="company">
                Empresas
            </g:link>
        </li>
        <li class="${controllerName == 'client' ? 'active' : ''}">
            <g:link controller="client">
                Clientes
            </g:link>
        </li>
        <li class="${controllerName == 'purchaseOrder' && actionName != 'stock' ? 'active' : ''}">
            <g:link controller="purchaseOrder" action="list">Pedidos</g:link>
        </li>
    </sec:ifAllGranted>
    <li class="${(controllerName == 'sale' && actionName == 'filterCreditSales') || controllerName == 'payment' ? 'active' : ''}">
        <g:link controller="sale" action="filterCreditSales">
            Abonos
        </g:link>
    </li>
    <li class="${controllerName == 'sale' && actionName != 'filterCreditSales' ? 'active' : ''}">
        <g:link controller="sale" action="create">
            Ventas
        </g:link>
    </li>
    <li class="${controllerName == 'expense' ? 'active' : ''}">
        <g:link controller="expense">
            Diario
        </g:link>
    </li>
</ul>
