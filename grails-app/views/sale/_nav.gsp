<ul class="nav nav-tabs">
    <li class="${actionName == 'create' ? 'active' : ''}">
        <g:link controller="sale">Venta</g:link>
    </li>

    <li class="${actionName == 'list' ? 'active' : ''}">
        <g:link controller="sale" action="list">Listar</g:link>
    </li>

    <li class="${actionName == 'list' ? 'active' : ''}">
        <g:link controller="sale" action="list">Estadistica</g:link>
    </li>
</ul>
<br>
