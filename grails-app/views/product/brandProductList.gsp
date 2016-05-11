<g:applyLayout name="threeColumns">
    <head>
        <title>Productos de marca</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, filter"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${brandProducts}">
            <p>${provider.name}</p>

            <table class="table table-hover table-striped">
                <thead>
                    <th width="1" class="text-center">#</th>
                    <th>Producto de marca</th>
                </thead>
                <tbody>
                    <g:each in="${brandProducts}" var="bp" status="index">
                        <tr id="${bp.id}">
                            <td class="text-center">
                                ${index + 1}
                            </td>
                            <td>
                                <g:link
                                    action="show"
                                    params="[id: bp.id, providerId: provider.id]">
                                    ${bp.name}
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
        <g:set var="isFiltered" value="${params.boolean('filtered')}"/>
        <g:set var="isEnabled" value="${params.boolean('enabled')}"/>

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!isFiltered ? 'active' : ''}">
                <a href="#create" aria-controls="create" role="tab" data-toggle="tab">
                    Crear
                </a>
            </li>
            <li role="presentation" class="${isFiltered ? 'active' : ''}">
                <a href="#filter" aria-controls="filter" role="tab" data-toggle="tab">
                    Filtrar
                </a>
            </li>
        </ul>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!isFiltered ? 'active' : 'no-active'}" id="create">
                <g:form action="${actionName}" autocomplete="off" params="[enabled: params.boolean('enabled')]">
                    <g:hiddenField name="providerId" value="${params?.providerId}"/>
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>
            <div role="tabpanel" class="tab-pane ${isFiltered ? 'active' : 'no-active'}" id="filter">
                <g:link
                    action="${actionName}"
                    params="[enabled: true, filtered: true, providerId: params?.providerId]"
                    class="btn btn-block btn-${isEnabled ? 'primary' : 'default'}">
                    Activos
                </g:link>

                <g:link
                    action="${actionName}"
                    params="[enabled: false, filtered: true, providerId: params?.providerId]"
                    class="btn btn-block btn-${!isEnabled ? 'primary' : 'default'}">
                    No activos
                </g:link>
            </div>
        </div>
    </content>
</g:applyLayout>
