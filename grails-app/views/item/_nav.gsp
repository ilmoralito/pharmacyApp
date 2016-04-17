<ul class="nav nav-tabs">
    <li class="${actionName == 'itemStock' ? 'active' : ''}">
        <g:link action="itemStock">Articulos</g:link>
    </li>

    <li class="${actionName == 'medicineStock' ? 'active' : ''}">
        <g:link action="medicineStock">Medicinas</g:link>
    </li>

    <li class="${actionName == 'brandProductStock' ? 'active' : ''}">
        <g:link action="brandProductStock">Productos</g:link>
    </li>
</ul>
<br>
