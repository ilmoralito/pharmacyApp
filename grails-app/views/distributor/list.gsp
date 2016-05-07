<g:applyLayout name="threeColumns">
    <head>
        <title>Distribuidores</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${dealers}">
            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Telefono</th>
                    <th>Limite</th>
                    <th>Contacto</th>
                    <th>Email</th>
                    <th>Telefono</th>
                </thead>
                <tbody>
                    <g:each in="${dealers}" var="dealer">
                        <tr>
                            <td>
                                <g:link action="show" id="${dealer.id}">
                                    ${dealer.name}
                                </g:link>
                            </td>
                            <td>${dealer.telephoneNumber}</td>
                            <td>${dealer.daysToPay}</td>
                            <td>${dealer.contact.fullName}</td>
                            <td>${dealer.contact.email}</td>
                            <td>${dealer.contact.telephoneNumber}</td>
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
        <g:set var="filtered" value="${params.boolean('filtered')}"/>
        <g:set var="isEnabled" value="${params.boolean('enabled')}"/>

        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!filtered ? 'active' : ''}">
                <a href="#create" aria-controls="create" role="tab" data-toggle="tab">
                    Crear
                </a>
            </li>
            <li role="presentation" class="${filtered ? 'active' : ''}">
                <a href="#filter" aria-controls="filter" role="tab" data-toggle="tab">
                    Filtrar
                </a>
            </li>
        </ul>
        <br>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!filtered ? 'active' : ''}" id="create">
                <g:form action="list" autocomplete="off" params="[enabled: params.boolean('enabled')]">
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${filtered ? 'active' : ''}" id="filter">
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
