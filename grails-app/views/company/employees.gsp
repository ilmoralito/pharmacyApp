<g:applyLayout name="threeColumns">
    <head>
        <title>Empleados</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, filterTable, alertBox"/>
    </head>

    <content tag="main">
        <g:link controller="company">Regresar</g:link>
        <br>

        <g:if test="${employees}">
            <table id="employees" class="table table-hover">
                <caption>${company.name}</caption>
                <colgroup>
                    <col span="1" style="width: 99%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>Nombre y apellido</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${employees}" var="employee">
                        <tr>
                            <td class="hide data">
                                ${employee.fullName} ${employee.inss}
                            </td>
                            <td>
                                <g:link action="showEmployee" id="${employee.id}">
                                    <g:fieldValue bean="${employee}" field="fullName"/>
                                </g:link>
                            </td>
                            <td class="text-center">
                                <g:link
                                    action="removeEmployee"
                                    params="[id: company.id, employeeId: employee.id]"
                                    class="remove">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p><br>Nada que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="employees" params="[id: company.id]">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="employees" params="[id: company.id, tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
            <g:form action="addEmployee" autocomplete="off">
                <g:hiddenField name="id" value="${company.id}"/>
                <g:render template="formEmployee"/>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <div class="form-group">
                <label>Por Nombre o INSS</label>
                <g:textField name="search" class="form-control"/>
            </div>
        </g:else>
    </content>
</g:applyLayout>
