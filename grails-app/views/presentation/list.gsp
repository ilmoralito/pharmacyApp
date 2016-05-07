<g:applyLayout name="threeColumns">
    <head>
        <title>Presentaciones</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, updateName"/>
    </head>

    <content tag="main">
        <g:if test="${presentations}">
            <table class="table table-hover">
                <thead>
                    <th width="1" class="text-center">#</th>
                    <th>Nombre de presentacion</th>
                </thead>
                <tbody>
                    <g:each in="${presentations}" var="presentation" status="idx">
                        <tr>
                            <td class="text-center">${idx + 1}</td>
                            <td>
                                <g:link action="show" id="${presentation.id}">
                                    ${presentation.name}
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
                    Presentacion
                </a>
            </li>
            <li role="presentation" class="${isFiltered ? 'active' : 'no-active'}">
                <a href="#detail" aria-controls="detail" role="tab" data-toggle="tab">
                    <small>Medida</small>
                </a>
            </li>
        </ul>
        <br>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!isFiltered ? 'active' : 'no-active'}" id="brand">
                <g:form action="${actionName}" autocomplete="off">
                    <g:render template="form"/>

                    <g:if test="${measures}">
                        <label>Filtrar medidas</label>
                        <pharmacyApp:measures/>
                    </g:if>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${isFiltered ? 'active' : 'no-active'}" id="detail">
                <g:form controller="measure" action="create" autocomplete="off">
                    <div class="form-group">
                        <label for="name">Medida</label>
                        <g:textField name="name" class="form-control"/>
                    </div>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>

                <g:if test="${measures}">
                    <table class="table table-hover">
                        <thead>
                           <th>Medidas</th>
                        </thead>
                        <tbody>
                            <g:each in="${measures}" var="measure">
                                <tr>
                                    <td
                                        class="trigger"
                                        data-id="${measure.id}"
                                        data-name="${measure.name}">
                                        ${measure.name}
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </g:if>
            </div>
        </div>

        <!--SAD solution T.T-->
        <g:javascript>
            window.ajaxPATH = "${createLink(controller: 'measure', action: 'update')}"
        </g:javascript>
    </content>
</g:applyLayout>
