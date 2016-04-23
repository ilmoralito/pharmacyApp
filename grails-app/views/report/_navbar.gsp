<ul class="nav nav-tabs">
    <li class="${actionName == 'sales' ? 'active' : ''}">
        <g:link action="sales">Ventas</g:link>
    </li>

    <li class="${actionName == 'stock' ? 'active' : ''}">
        <g:link action="stock">Inventario</g:link>
    </li>

    <li class="${actionName == 'clients' ? 'active' : ''}">
        <g:link action="clients">Clientes</g:link>
    </li>

    <li class="${actionName == 'employees' ? 'active' : ''}">
        <g:link action="employees">A empresas</g:link>
    </li>
</ul>
<br>
