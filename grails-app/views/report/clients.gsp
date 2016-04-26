<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${clients}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <th>Cliente</th>
                    <th>Numero de compras</th>
                </thead>
                <tbody>
                    <g:each in="${clients}" var="c">
                        <tr>
                            <td>
                                <g:link
                                    action="detail"
                                    params="[id:c.id, client: c.client, field: params?.field ?: 'month']">
                                    ${c.client}
                                </g:link>
                            </td>
                            <td>${c.quantity}</td>
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
        <p>Intervalos</p>
        <g:form name="byMonth" action="clients" class="intervals">
            <g:hiddenField name="field" value="month"/>
            <g:submitButton name="send" value="Por mes" class="btn btn-default btn-block"/>
        </g:form>

        <g:form name="byWeek" action="clients" class="intervals">
            <g:hiddenField name="field" value="week"/>
            <g:submitButton name="send" value="Por semana" class="btn btn-default btn-block"/>
        </g:form>

        <g:form name="byYear" action="clients" class="intervals">
            <g:hiddenField name="field" value="year"/>
            <g:submitButton name="send" value="Por ano" class="btn btn-default btn-block"/>
        </g:form>

        <g:form action="clients" autocomplete="off">
            <g:hiddenField name="field" value="custom"/>
            <g:render template="filterForm"/>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
