<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${clients}">
            <table class="table table-hover">
                <thead>
                    <th>Clientes</th>
                </thead>
                <tbody>
                    <g:each in="${clients}" var="client">
                        <tr>
                            <td>
                                <g:link action="show" id="${client.id}">
                                    ${client.fullName}
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

    <content tag="col1">
        <g:form action="list" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
