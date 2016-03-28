<g:applyLayout name="threeColumns">
    <head>
        <title>Medicinas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${medicines}">
            <p><br>${provider.name}</p>

            <table class="table table-hover table-striped">
                <thead>
                    <th width="1" class="text-center">#</th>
                    <th>Nombre</th>
                </thead>
                <tbody>
                    <g:each in="${medicines}" var="medicine" status="index">
                        <tr id="${medicine.id}">
                            <td class="text-center">
                                ${index + 1}
                            </td>
                            <td>
                                <g:link
                                    action="show"
                                    params="[id: medicine.id, providerId: params?.providerId]">
                                    ${medicine.name}
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
        <br>

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
