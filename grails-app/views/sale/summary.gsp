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
                    <col span="1" style="width: 50%;">
                    <col span="1" style="width: 50%;">
                </colgroup>
                <thead>
                    <th>Articulo</th>
                    <th>Cantidad</th>
                </thead>
                <tbody>
                    <g:each in="${saleDetails}" var="saleDetail">
                        <g:set var="data" value="${saleDetail}"/>
                        <tr>
                            <td>
                                <g:if test="${item instanceof ni.sb.MedicineOrder}">
                                    ${item} ${item.presentation} ${item.measure}
                                </g:if>
                                <g:elseif test="${item instanceof ni.sb.BrandProductOrder}">
                                    ${item} ${saleDetail.brand} ${detail}
                                </g:elseif>
                                <g:else>
                                    ${saleDetail}
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
                Datos
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
