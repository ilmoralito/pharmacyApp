<g:applyLayout name="twoColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${expenses}">
            <p>Gastos diario de ${month} ${year}</p>

            <table class="table table-hover table-bordered">
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 74%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Dia</th>
                    <th>Usuario</th>
                    <th>Descripcion</th>
                    <th>Monto</th>
                </thead>
                <tbody>
                    <g:each in="${expenses}" var="e">
                        <tr>
                            <td rowspan="${e.expenses?.size() ?: 0}">
                                ${e.day}
                            </td>
                            <g:if test="${e.expenses}">
                                <g:each in="${e.expenses}" var="expense">
                                    <tr>
                                        <td><g:fieldValue bean="${expense}" field="user.fullName"/></td>
                                        <td><g:fieldValue bean="${expense}" field="description"/></td>
                                        <td><g:fieldValue bean="${expense}" field="quantity"/></td>
                                    </tr>
                                </g:each>
                            </g:if>
                            <g:else>
                                <tr>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>-</td>
                                </tr>
                            </g:else>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="2"></td>
                        <td><%--${expenses.quantity.sum()}--%></td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
