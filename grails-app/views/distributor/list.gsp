<g:applyLayout name="threeColumns">
    <head>
        <title>Distribuidores</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${dealers}">
            <table class="table">
                <thead>
                    <th>Distribuidor</th>
                    <th>Contacto</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${dealers}" var="dealer">
                        <tr>
                            <td>
                                <g:link action="show" id="${dealer.id}">
                                    ${dealer.name}
                                </g:link>
                            </td>
                            <td>${dealer.telephoneNumber}</td>
                            <td></td>
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
