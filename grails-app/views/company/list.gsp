<g:applyLayout name="threeColumns">
    <head>
        <title>Empresas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${companies}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 90%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Telefono</th>
                    <th>Empleados</th>
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
        <g:set var="isFiltered" value="${params.boolean('filtered') ?: false}"/>
        <g:set var="isEnabled" value="${params.boolean('enabled')}"/>

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!isFiltered ? 'active' : 'no-active'}">
                <a href="#create" aria-controls="create" role="tab" data-toggle="tab">
                    Crear
                </a>
            </li>
            <li role="presentation" class="${isFiltered ? 'active' : 'no-active'}">
                <a href="#filter" aria-controls="filter" role="tab" data-toggle="tab">
                    Filtrar
                </a>
            </li>
        </ul>
        <br>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!isFiltered ? 'active' : 'no-active'}" id="create">
                <g:form action="list" autocomplete="off">
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${isFiltered ? 'active' : 'no-active'}" id="filter">
                <g:link
                    action="list"
                    params="[enabled: true, filtered: true]"
                    class="btn btn-block btn-${isEnabled ? 'primary' : 'default'}">
                    Activos
                </g:link>

                <g:link
                    action="list"
                    params="[enabled: false, filtered: true]"
                    class="btn btn-block btn-${!isEnabled ? 'primary' : 'default'}">
                    No activos
                </g:link>
            </div>
        </div>
    </content>
</g:applyLayout>
