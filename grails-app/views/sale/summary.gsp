<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${saleDetails}">
            <table class="table table-hover">
                 <colgroup>
                    <col span="1" style="width: 30%;">
                    <col span="1" style="width: 70%;">
                </colgroup>
                <thead>
                    <th>Articulo</th>
                    <th>Unidades vendidas</th>
                </thead>
                <tbody>
                    <g:each in="${saleDetails}" var="saleDetail">
                        <g:set var="data" value="${saleDetail}"/>
                        <tr>
                            <td>
                                <g:if test="${saleDetail.item instanceof ni.sb.MedicineOrder}">
                                    ${saleDetail.item} ${saleDetail.item.presentation} ${saleDetail.item.measure}
                                </g:if>
                                <g:elseif test="${saleDetail.item instanceof ni.sb.BrandProductOrder}">
                                    ${saleDetail.item} ${saleDetail.item.brand} ${saleDetail.item.detail}
                                </g:elseif>
                                <g:else>
                                    ${saleDetail.item}
                                </g:else>
                            </td>
                            <td>${saleDetail.quantity}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
    <content tag="col1">
        <div class="panel panel-default">
            <div class="panel-heading">
                Resumen
            </div>
            <table class="table table-hover">
                <tbody>
                    <tr>
                        <td>Saldo</td>
                        <td>${balance}</td>
                    </tr>
                    <tr>
                        <td>Gasto</td>
                        <td>
                            <g:link controller="expense">
                                ${expenseBalance ?: 0}
                            </g:link>
                        </td>
                    </tr>
                    <tr>
                        <td>Total caja</td>
                        <td>${balance - expenseBalance}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </content>
</g:applyLayout>
