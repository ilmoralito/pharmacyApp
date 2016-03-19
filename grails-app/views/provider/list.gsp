<g:applyLayout name="threeColumns">
    <head>
        <title>Proveedores</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:set var="status" value="${params.boolean('status')}"/>

        <div class="btn-group pull-right">
            <g:link action="list" params="[status: true]" class="${status ? 'active' : ''} btn btn-default">
                Activos
            </g:link>
            <g:link action="list" params="[status: false]" class="${!status ? 'active' : ''} btn btn-default">
                Inactivos
            </g:link>
        </div>

        <g:if test="${providers}">
            <table class="table table-hover">
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
                                    params="[providerId: provider.id]">
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
        <g:form action="list" autocomplete="off" params="[status: true]">
            <g:render template="form"/>

            <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
