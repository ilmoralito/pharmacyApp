<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
        <g:link action="${actionName}">Crear</g:link>
    </li>
    <li role="presentation" class="${params.tab == 'filter' ? 'active' : '' }">
        <g:link action="${actionName}" params="[tab: 'filter']">Filtrar</g:link>
    </li>
</ul>
