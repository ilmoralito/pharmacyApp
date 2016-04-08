<g:applyLayout name="threeColumns">
    <head>
        <title>Laboratorios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${providers}">
            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 55%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 15%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Productos</th>
                </thead>
                <tbody>
                    <g:each in="${providers}" var="provider">
                        <tr>
                            <td>
                                <g:link action="show" params="[id: provider.id]">
                                    ${provider}
                                </g:link>
                            </td>
                            <td>${provider.address}</td>
                            <td>${provider.telephoneNumber}</td>
                            <td>
                                <g:link
                                    controller="product"
                                    action="productList"
                                    params="[providerId: provider.id, enabled: true]">
                                    Productos
                                </g:link>
                                <g:if test="${!provider.products}">*</g:if>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <br><br>
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
                <g:form action="list" autocomplete="off" params="[enabled: params.boolean('enabled')]">
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
