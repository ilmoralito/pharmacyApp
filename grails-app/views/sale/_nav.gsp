<ul class="nav nav-tabs">
    <li class="${actionName == 'createSale' ? 'active' : ''}">
        <g:link controller="sale">Venta</g:link>
    </li>

    <li class="${actionName in ['list', 'detail'] ? 'active' : ''}">
        <g:link controller="sale" action="list">Listar</g:link>
    </li>

    <li class="${actionName == 'summary' ? 'active' : ''}">
        <g:link controller="sale" action="summary">Sumario</g:link>
    </li>
</ul>
<br>
