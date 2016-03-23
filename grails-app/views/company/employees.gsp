<g:applyLayout name="threeColumns">
    <head>
        <title>Empleados</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, employees"/>
    </head>

    <content tag="main">
        <g:link controller="company">Regresar</g:link>
        <br>

        <g:if test="${employees}">
            <h4>Empleados de ${company}</h4>

            <table id="employees" class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 50%;">
                    <col span="1" style="width: 45%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Nombre de empleado</th>
                    <th>INSS</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${employees}" var="employee">
                        <tr>
                            <td>
                                <g:link action="show" id="${employee.id}">
                                    ${employee.fullName}
                                </g:link>
                            </td>
                            <td>${employee.inss}</td>
                            <td>
                                <g:link
                                    action="removeEmployee"
                                    params="[id: company.id, employeeId: employee.id]">
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
        <br>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!filtered ? 'active' : ''}" id="create">
                <g:form action="addEmployee" autocomplete="off">
                    <g:hiddenField name="id" value="${company.id}"/>

                    <div class="form-group">
                        <g:textField
                            name="fullName"
                            value="${employee?.fullName}"
                            class="form-control"
                            placeholder="Nombre del empleado"/>
                    </div>

                    <div class="form-group">
                        <g:textField
                            name="inss"
                            value="${employee?.inss}"
                            class="form-control"
                            placeholder="Numero de INSS"/>
                    </div>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${filtered ? 'active' : ''}" id="filter">
                <div class="form-group">
                    <g:textField
                        name="search"
                        class="form-control"
                        placeholder="Por nombre o INSS"/>
                </div>
            </div>
        </div>
    </content>
</g:applyLayout>
