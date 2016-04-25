<g:applyLayout name="twoColumns">
    <head>
        <title>Existencias bajas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${items}">
            <p>Productos con bajas existencias</p>

            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <th>Producto</th>
                    <th>Cantidad actual</th>
                </thead>
                <tbody>
                    <g:each in="${items.sort { it.quantity }}" var="item">
                        <tr>
                            <td>
                                <g:if test="${item instanceof ni.sb.MedicineOrder}">
                                    ${item.product.name} ${item.presentation} ${item.measure}
                                </g:if>
                                <g:elseif test="${item instanceof ni.sb.BrandProductOrder}">
                                    ${item.product.name} ${item.brand} ${item.detail}
                                </g:elseif>
                                <g:else>
                                    ${item}
                                </g:else>
                            </td>
                            <td>${item.quantity}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
