<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${users}">
            <table class="table table-hover table-striped">
                <colgroup>
                   <col span="1" style="width: 45%;">
                   <col span="1" style="width: 45%;">
                   <col span="1" style="width: 5%;">
                   <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Telefono</th>
                    <th>Rol</th>
                </thead>
                <tbody>
                    <g:each in="${users}" var="user">
                        <tr>
                            <td>
                                <g:link action="show" id="${user.id}">
                                    ${user.fullName}
                                </g:link>
                            </td>
                            <td>${user.email}</td>
                            <td>${user.telephoneNumber}</td>
                            <td>${user.getAuthorities().authority.join(", ").tokenize("_")[1]}</td>
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

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!isFiltered ? 'active' : 'no-active'}" id="create">
                <g:form controller="user" action="list" autocomplete="off">
                    <g:render template="form"/>

                    <input type="submit" class="btn btn-primary btn-block" value="Agregar"/>
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
