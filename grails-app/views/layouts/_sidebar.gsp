<ul class="nav nav-pills nav-stacked">
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
