<g:applyLayout name="threeColumns">
    <head>
        <title>Presentaciones</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, updateName"/>
    </head>

    <content tag="main">
        <g:if test="${presentations}">
            <table class="table table-hover">
                <thead>
                    <th>Nombre de presentacion</th>
                </thead>
                <tbody>
                    <g:each in="${presentations}" var="presentation">
                        <tr>
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
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'measure' ? 'active' : ''}">
                <g:link action="list" class="tab-label-text">
                    Presentacion
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'measure' ? 'active' : '' }">
                <g:link action="list" params="[tab: 'measure']"  class="tab-label-text">
                    Medida
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'measure' ? 'active' : ''}">
            <g:form action="list" autocomplete="off">
                <g:render template="form"/>

                <g:if test="${measures}">
                    <label>Filtrar medidas</label>
                    <pharmacyApp:measures/>
                </g:if>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <g:form controller="measure" action="create" params="[tab: 'measure']" autocomplete="off">
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <g:textField name="name" class="form-control"/>
                </div>

                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>

            <g:if test="${measures}">
                <table class="table table-hover" style="margin-top: 10px;">
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
            <g:else>
                <p>No hay medidas registradas</p>
            </g:else>
        </g:else>

        <!--SAD solution T.T-->
        <g:javascript>
            window.ajaxPATH = "${createLink(controller: 'measure', action: 'update')}"
        </g:javascript>
    </content>
</g:applyLayout>
