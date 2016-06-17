<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
        <g:link action="list">${createTabText ?: "Crear"}</g:link>
    </li>
    <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
        <g:link action="list" params="[tab: 'filter']">${filterTabText ?: "Filtrar"}</g:link>
    </li>
</ul>
