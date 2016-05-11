<ul class="nav nav-tabs">
    <li class="${actionName == 'sales' ? 'active' : ''}">
        <g:link action="sales">Ventas</g:link>
    </li>

    <li
        role="presentation"
        class="${actionName == 'stock' ? 'active' : ''} dropdown">
        <g:link
            action="stock"
            class="dropdown-toggle"
            data-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false">
                Inventario
                <span class="caret"></span>
            </g:link>
            <ul class="dropdown-menu" role="menu">
                <li class="${!params?.flag ? 'active' : '' }">
                    <g:link action="stock">
                        Articulos
                    </g:link>
                </li>
                <li class="${params?.flag == 'medicines' ? 'active' : ''}">
                    <g:link action="stock" params="[flag: 'medicines']">
                        Medicinas
                    </g:link>
                </li>
                <li class="${params?.flag == 'brandProducts' ? 'active' : ''}">
                    <g:link action="stock" params="[flag: 'brandProducts']">
                        Marcas
                    </g:link>
                </li>
            </ul>
    </li>

    <li class="${actionName == 'clients' || actionName == 'detail' ? 'active' : ''}">
        <g:link action="clients">Clientes</g:link>
    </li>

    <li class="${actionName == 'expenses' || actionName == 'expensesDetail' ? 'active' : ''}">
        <g:link action="expenses">Gastos diarios</g:link>
    </li>

    <li class="${actionName == 'employees' ? 'active' : ''}">
        <g:link action="employees">A empresas</g:link>
    </li>
</ul>
