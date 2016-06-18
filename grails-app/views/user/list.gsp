<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${users}">
            <table class="table table-hover">
                <thead>
                    <th>Nombre</th>
                </thead>
                <tbody>
                    <g:each in="${users}" var="user">
                        <tr>
                            <td>
                                <g:link action="show" id="${user.id}">
                                    <g:fieldValue bean="${user}" field="fullName"/>
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
        <g:render template="/shared/createFilterNav"/>

        <g:if test="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
            <g:form action="create" autocomplete="off">
                <g:render template="form"/>

                <input type="submit" class="btn btn-primary btn-block" value="Agregar"/>
            </g:form>
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
