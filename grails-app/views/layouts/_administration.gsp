<g:set var="controllers" value="['distributor', 'provider', 'product', 'presentation', 'brand', 'company', 'client', 'purchaseOrder']"/>

<sec:ifAllGranted roles="ROLE_ADMIN">
    <li class="dropdown ${controllerName in controllers ? 'active' : ''}">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            Administrar
            <span class="caret"></span>
        </a>

        <ul class="dropdown-menu" role="menu">
            <li class="${controllerName == 'distributor' ? 'active' : ''}">
                <g:link controller="distributor">
                    Distribuidores
                </g:link>
            </li>
            <li class="${controllerName in ['provider', 'product'] ? 'active' : ''}">
                <g:link controller="provider">
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
                <g:link controller="company">
                    Empresas
                </g:link>
            </li>
            <li class="${controllerName == 'client' ? 'active' : ''}">
                <g:link controller="client">
                    Clientes
                </g:link>
            </li>
            <li class="divider"></li>
            <li class="${controllerName == 'purchaseOrder' && actionName != 'stock' ? 'active' : ''}">
                <g:link controller="purchaseOrder" action="list">Pedidos</g:link>
            </li>
        </ul>
    </li>
</sec:ifAllGranted>
