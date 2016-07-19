<g:applyLayout name="threeColumns">
    <head>
        <title>Laboratorios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${providers}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 94%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>Laboratorios</th>
                    <th>Productos</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${providers}" var="provider">
                        <tr>
                            <td>
                                <g:link action="show" params="[id: provider.id]">
                                    <g:fieldValue bean="${provider}" field="name"/>
                                </g:link>
                            </td>
                            <td>
                                <g:link
                                    controller="product"
                                    action="productList"
                                    params="[providerId: provider.id]">
                                    Productos
                                </g:link>
                            </td>
                            <td>
                                <g:if test="${!provider.products}">
                                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                </g:if>
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

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="list">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="list" params="[tab: 'filter']">
                    Filtrar
                </g:link>
            </li>
        </ul>

        <div class="tab-content">
            <g:if test="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:form name="createProviderForm" action="list" autocomplete="off">
                    <g:render template="form"/>

                    <g:submitButton name="sendProviderForm" value="Confirmar" class="btn btn-primary btn-block"/>
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
        </div>
    </content>
</g:applyLayout>
