<g:set var="parameters" value="[providerId: provider.id, enabled: true]"/>

<ul class="nav nav-tabs">
    <li class="${actionName in ['productList', 'show'] ? 'active' : ''}">
        <g:link
            action="productList"
            params="${parameters}">
            Productos
        </g:link>
    </li>
    <li class="${actionName == 'medicineList' ? 'active' : ''}">
        <g:link
            action="medicineList"
            params="${parameters}">
            Medicamentos
        </g:link>
    </li>
    <li class="${actionName == 'brandProductList' ? 'active' : ''}">
        <g:link
            action="brandProductList"
            params="${parameters}">
            Marcas
        </g:link>
    </li>
</ul>
