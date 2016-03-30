<g:applyLayout name="threeColumns">
    <head>
        <title>Medidas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, updateName"/>
    </head>

    <content tag="main">
        <g:if test="${brands}">
            <table class="table table-hover table-striped">
                <thead>
                    <th width="1" class="text-center">#</th>
                    <th>Marcas</th>
                </thead>
                <tbody>
                    <g:each in="${brands}" var="brand" status="idx">
                        <tr>
                            <td class="text-center">${idx + 1}</td>
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
        <g:set var="isFiltered" value="${params.boolean('filtered')}"/>

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!isFiltered ? 'active' : 'no-active'}">
                <a href="#brand" aria-controls="brand" role="tab" data-toggle="tab">
                    Marca
                </a>
            </li>
            <li role="presentation" class="${isFiltered ? 'active' : 'no-active'}">
                <a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">
                    Detalle
                </a>
            </li>
        </ul>
        <br>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!isFiltered ? 'active' : 'no-active'}" id="brand">
                <g:form action="${actionName}" autocomplete="off">
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${isFiltered ? 'active' : 'no-active'}" id="detail">
                <g:form controller="detail" action="create" autocomplete="off">
                    <div class="form-group">
                        <g:textField name="name" class="form-control" placeholder="Nombre"/>
                    </div>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>

                <table class="table table-hover table-striped">
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
            </div>
        </div>

        <!--SAD solution T.T-->
        <g:javascript>
            window.ajaxPATH = "${createLink(controller: 'detail', action: 'update')}"
        </g:javascript>
    </content>
</g:applyLayout>
