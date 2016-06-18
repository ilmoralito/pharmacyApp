<g:applyLayout name="twoColumns">
    <head>
        <title>Gasto diario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${data}">
            <g:each in="${data}" var="d">
                <table class="table table-hover">
                    <caption>${d.year}</caption>
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 75%;">
                    </colgroup>
                    <thead>
                        <th>Mes</th>
                        <th>Numero de gastos</th>
                        <th>Monto del mes</th>
                    </thead>
                    <tbody>
                        <g:each in="${d.months}" var="m" status="idx">
                            <tr>
                                <td>
                                    <g:if test="${m.quantity}">
                                        <g:link action="expensesDetail" params="[y: d.year, m: idx + 1]">
                                            ${m.name}
                                        </g:link>
                                    </g:if>
                                    <g:else>
                                        ${m.name}
                                    </g:else>
                                </td>
                                <td>${m.quantity}</td>
                                <td>${m.total}</td>
                            </tr>
                        </g:each>
                        <tr>
                            <td></td>
                            <td>${d.months.quantity.sum()}</td>
                            <td>${d.months.total.sum()}</td>
                        </tr>
                    </tbody>
                </table>
            </g:each>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
