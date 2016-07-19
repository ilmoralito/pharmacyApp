<g:applyLayout name="threeColumns">
    <head>
        <title>Distribuidores</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${dealers}">
            <table class="table table-hover">
                <thead>
                    <th>Distribuidores</th>
                </thead>
                <tbody>
                    <g:each in="${dealers}" var="dealer">
                        <tr>
                            <td>
                                <g:link action="show" id="${dealer.id}">
                                    <g:fieldValue bean="${dealer}" field="name"/>
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
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="list">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="list" params="[tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter'}">
            <g:link action="create" class="btn btn-primary btn-block">Crear distribuidor</g:link>
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
