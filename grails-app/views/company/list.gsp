<g:applyLayout name="threeColumns">
    <head>
        <title>Empresas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${companies}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 68%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 19%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Telefono</th>
                    <th>Empleados</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${companies}" var="company">
                        <tr>
                            <td>
                                <g:link action="show" id="${company.id}">
                                    ${company.name}
                                </g:link>
                            </td>
                            <td>${company.telephoneNumber}</td>
                            <td>
                                <g:link action="employees" id="${company.id}">
                                    Empleados
                                </g:link>
                            </td>
                            <td>
                                <g:link action="updateEnabledState" id="${company.id}">
                                    <g:if test="${company.enabled}">
                                        Habilitado
                                    </g:if>
                                    <g:else>
                                        No habilitado
                                    </g:else>
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
