<ul class="nav nav-tabs">
    <li class="${actionName in ['productList', 'show'] ? 'active' : ''}">
        <g:link
            action="productList"
            params="[providerId: params?.providerId, enabled: true]">
            Productos
        </g:link>
    </li>
    <li class="${actionName == 'medicineList' ? 'active' : ''}">
        <g:link
            action="medicineList"
            params="[providerId: params?.providerId, enabled: true]">
            Medicamentos
        </g:link>
    </li>
    <li class="${actionName == 'brandList' ? 'active' : ''}">
        <g:link
            action="brandList"
            params="[providerId: params?.providerId, enabled: true]">
            Marcas
        </g:link>
    </li>
</ul>
