<g:applyLayout name="threeColumns">
    <head>
        <title>Empleados</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, filterTable"/>
    </head>

    <content tag="main">
        <g:link controller="company">Regresar</g:link>
        <br>

        <g:if test="${employees}">
            <p>Empleados de ${companyName}</p>

            <table id="employees" class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 80%;">
                    <col span="1" style="width: 5%%;">
                    <col span="1" style="width: 5%%;">
                    <col span="1" style="width: 5%%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Nombre y apellido</th>
                    <th>INSS</th>
                    <th>Cedula</th>
                    <th>Telefono</th>
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
                            <td><g:fieldValue bean="${employee}" field="inss"/></td>
                            <td><g:fieldValue bean="${employee}" field="identificationCard"/></td>
                            <td><g:fieldValue bean="${employee}" field="telephoneNumber"/></td>
                            <td class="text-center">
                                <g:link
                                    action="removeEmployee"
                                    params="[id: companyId, employeeId: employee.id]">
                                    <span class="glyphicon glyphicon-trash"></span>
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
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!filtered ? 'active' : ''}">
                <a href="#create" aria-controls="create" role="tab" data-toggle="tab">
                    Crear
                </a>
            </li>
            <li role="presentation" class="${filtered ? 'active' : ''}">
                <a href="#filter" aria-controls="filter" role="tab" data-toggle="tab">
                    Filtrar
                </a>
            </li>
        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!filtered ? 'active' : ''}" id="create">
                <g:form action="addEmployee" autocomplete="off">
                    <g:hiddenField name="id" value="${companyId}"/>
                    <g:render template="formEmployee"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${filtered ? 'active' : ''}" id="filter">
                <div class="form-group">
                    <label>Por Nombre o INSS</label>
                    <g:textField
                        name="search"
                        class="form-control"/>
                </div>
            </div>
        </div>
    </content>
</g:applyLayout>
