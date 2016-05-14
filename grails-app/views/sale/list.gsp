<g:applyLayout name="twoColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filterTable"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${sales}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 90%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th class="text-center">Hora</th>
                    <th>
                        <input
                            id="search"
                            class="form-control input-sm"
                            style="font-weight: normal;"
                            placeholder="Filtrar"
                            autofocus="true">
                    </th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${sales}" var="sale">
                        <tr>
                            <td><g:formatDate format="HH:mm" date="${sale.dateCreated}"/></td>
                            <td class="data">
                                <g:link action="detail" id="${sale.id}">
                                    <g:if test="${sale instanceof ni.sb.CashSale}">
                                        ${sale.client.fullName}
                                    </g:if>

                                    <g:if test="${sale instanceof ni.sb.CreditSale}">
                                        ${sale.employee.fullName}
                                    </g:if>

                                    <g:if test="${sale.canceled}">
                                        <span class="label label-info">Anulada</span>
                                    </g:if>
                                </g:link>
                            </td>
                            <td>
                                <g:if test="${sale instanceof ni.sb.CreditSale}">
                                    <span class="label label-warning">Credito</span>
                                </g:if>
                            </td>
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
