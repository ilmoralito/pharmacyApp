<g:applyLayout name="twoColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${expenses}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 60%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <thead>
                    <th>Usuario</th>
                    <th>Descripcion</th>
                    <th>Monto</th>
                </thead>
                <tbody>
                    <g:each in="${expenses}" var="e">
                        <tr>
                            <td><g:fieldValue bean="${e}" field="user.fullName"/></td>
                            <td><g:fieldValue bean="${e}" field="description"/></td>
                            <td><g:fieldValue bean="${e}" field="quantity"/></td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="2"></td>
                        <td>${expenses.quantity.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
