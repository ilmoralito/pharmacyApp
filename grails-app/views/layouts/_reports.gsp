<sec:ifAllGranted roles="ROLE_ADMIN">
    <li class="dropdown ${controllerName == 'report' ? 'active' : ''}">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            Reportes
            <span class="caret"></span>
        </a>

        <ul class="dropdown-menu" role="menu">
            <li class="${actionName == 'sales' ? 'active' : ''}">
                <g:link controller="report" action="sales">
                    Ventas
                </g:link>
            </li>
            <li class="${actionName == 'stock' ? 'active' : ''}">
                <g:link controller="report" action="stock">
                    Inventario
                </g:link>
            </li>
            <li class="${actionName == 'clients' ? 'active' : ''}">
                <g:link controller="report" action="clients">
                    Clientes
                </g:link>
            </li>
            <li class="${actionName == 'expenses' ? 'active' : ''}">
                <g:link controller="report" action="expenses">
                    Gastos diarios
                </g:link>
            </li>
            <li class="${actionName == 'employees' ? 'active' : ''}">
                <g:link controller="report" action="employees">
                    A empresas
                </g:link>
            </li>
        </ul>
    </li>
</sec:ifAllGranted>
