<g:applyLayout name="twoColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
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
                    <th class="text-center">#</th>
                    <th>
                        <input
                            id="filter"
                            class="form-control input-sm"
                            style="font-weight: normal;"
                            placeholder="Filtrar"
                            autofocus="true">
                    </th>
                </thead>
                <tbody>
                    <g:each in="${sales}" var="sale" status="idx">
                        <tr>
                            <td>${idx + 1}</td>
                            <td>
                                <g:link action="detail" id="${sale.id}">
                                    ${sale.toName}
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
