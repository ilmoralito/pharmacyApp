<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${clients}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 10%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                </thead>
                <tbody>
                    <g:each in="${clients}" var="client">
                        <tr>
                            <td>
                                <g:link action="show" id="${client.id}">
                                    ${client.fullName}
                                </g:link>
                            </td>
                            <td>${client.email}</td>
                            <td>${client.address}</td>
                            <td>${client.telephoneNumber}</td>
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
