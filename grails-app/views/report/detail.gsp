<g:applyLayout name="twoColumns">
    <head>
        <title>Detalle de cliente</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <p>${params?.client} por <pharmacyApp:field field="${params?.field}"/></p>
        <table class="table table-hover">
            <colgroup>
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 75%;">
            </colgroup>
            <thead>
                <th>Articulo</th>
                <th>Veces comprado</th>
            </thead>
            <tbody>
                <g:each in="${result}" var="r">
                    <tr>
                        <td>
                            <g:if test="${r.item instanceof ni.sb.MedicineOrder}">
                                ${r.item} ${r.item.presentation} ${r.item.measure}
                            </g:if>
                            <g:elseif test="${r.item instanceof ni.sb.BrandProductOrder}">
                                ${r.item} ${r.item.brand} ${r.item.detail}
                            </g:elseif>
                            <g:else>
                                ${r.item}
                            </g:else>
                        </td>
                        <td>${r.quantity}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>
</g:applyLayout>
