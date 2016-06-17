<g:applyLayout name="threeColumns">
    <head>
        <title>Empresas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${companies}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 89%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>Empresas</th>
                    <th>Empleados</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${companies}" var="company">
                        <tr>
                            <td>
                                <g:link action="show" id="${company.id}">${company.name}</g:link>
                            </td>
                            <td>
                                <g:link action="employees" id="${company.id}">Empleados</g:link>
                            </td>
                            <td>
                                <g:if test="${!company.employees}">
                                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                </g:if>
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
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="list">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : '' }">
                <g:link action="list" params="[tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
            <g:link action="create" class="btn btn-primary btn-block">Crear empresa</g:link>
        </g:if>
        <g:else>
            <g:link
                action="list"
                params="[enabled: true, tab: 'filter']"
                class="btn btn-block btn-${params.boolean('enabled') || params.enabled == null ? 'primary' : 'default'}">
                Activos
            </g:link>

            <g:link
                action="list"
                params="[enabled: false, tab: 'filter']"
                class="btn btn-block btn-${params.boolean('enabled') == false ? 'primary' : 'default'}">
                No activos
            </g:link>
        </g:else>
    </content>
</g:applyLayout>
