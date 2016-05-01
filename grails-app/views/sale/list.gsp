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
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 99%;">
                </colgroup>
                <thead>
                    <th class="text-center">ID</th>
                    <th>
                        <input
                            id="search"
                            class="form-control input-sm"
                            style="font-weight: normal;"
                            placeholder="Filtrar"
                            autofocus="true">
                    </th>
                </thead>
                <tbody>
                    <g:each in="${sales}" var="sale">
                        <tr>
                            <td>${sale.id}</td>
                            <td class="data">
                                <g:link action="detail" id="${sale.id}">
                                    ${sale.client.fullName}

                                    <g:if test="${sale.canceled}">
                                        <span class="label label-info">Anulada</span>
                                    </g:if>
                                </g:link>
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
