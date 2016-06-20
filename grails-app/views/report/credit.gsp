<g:applyLayout name="twoColumns">
    <head>
        <title>Credito</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${companies}">
            <table class="table table-hover">
                <caption>Empresas con trabajores con credito</caption>
                <thead>
                    <th>Nombre</th>
                </thead>
                <tbody>
                    <g:each in="${companies}" var="c">
                        <tr>
                            <td>
                                <g:link action="creditDetail" id="${c.id}">
                                    <g:fieldValue bean="${c}" field="name"/>
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
