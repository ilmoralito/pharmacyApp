<g:applyLayout name="threeColumns">
    <head>
        <title>Medidas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, updateName"/>
    </head>

    <content tag="main">
        <g:if test="${brands}">
            <table class="table table-hover">
                <thead>
                    <th>Marcas</th>
                </thead>
                <tbody>
                    <g:each in="${brands}" var="brand">
                        <tr>
                            <td>
                                <g:link action="show" id="${brand.id}">
                                    ${brand.name}
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
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'detail' ? 'active' : ''}">
                <g:link action="list">Marca</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'detail' ? 'active' : ''}">
                <g:link action="list" params="[tab: 'detail']">Detalle</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'detail'}">
            <g:form action="${actionName}" autocomplete="off">
                <g:render template="form"/>

                <g:if test="${details}">
                    <label>Filtrar detalles</label>
                    <pharmacyApp:details/>
                </g:if>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <g:form controller="detail" action="create" autocomplete="off">
                <div class="form-group">
                    <g:textField name="name" class="form-control" placeholder="Nombre"/>
                </div>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>

            <g:if test="${details}">
                <table class="table table-hover" style="margin-top: 5px;">
                    <thead>
                       <th>Nombre</th>
                    </thead>
                    <tbody>
                        <g:each in="${details}" var="detail">
                            <tr>
                                <td
                                    class="trigger"
                                    data-id="${detail.id}"
                                    data-name="${detail.name}">
                                    ${detail.name}
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
        </g:else>

        <g:javascript>
            window.ajaxPATH = "${createLink(controller: 'detail', action: 'update')}"
        </g:javascript>
    </content>
</g:applyLayout>
