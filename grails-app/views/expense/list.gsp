<g:applyLayout name="threeColumns">
    <head>
        <title>Gasto diario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:if test="${expenses}">
            <table class="table table-hover">
                <caption>Gasto diario</caption> 
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 63%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>#</th>
                    <th>Hora</th>
                    <th>Realizado por</th>
                    <th>Razon del gasto</th>
                    <th>Monto</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${expenses}" var="expense" status="i">
                        <tr>
                            <td>${i+1}</td>
                            <td>
                                <g:formatDate date="${expense.dateCreated}" formatName="hour.date.format"/>
                            </td>
                            <td><g:fieldValue bean="${expense}" field="user.fullName"/></td>
                            <td><g:fieldValue bean="${expense}" field="description" /></td>
                            <td><g:fieldValue bean="${expense}" field="quantity"/></td>
                            <td>
                                <sec:ifAllGranted roles="ROLE_ADMIN">
                                    <g:link action="delete" id="${expense.id}">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </sec:ifAllGranted>
                            </td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="4"></td>
                        <td>${expenses.quantity.sum()}</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin datos que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="list">Crear</g:link>
            </li>
            <li role="presentation" class="${params.tab == 'filter' ? 'active' : '' }">
                <g:link action="list" params="[tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
            <g:form action="create" autocomplete="off">
                <div class="form-group">
                    <label for="description">Motivo del gasto</label>
                    <g:textArea
                        name="description"
                        class="form-control"/>
                </div>
            
                <div class="form-group">
                    <label for="quantity">Monto</label>
                    <g:textField name="quantity" class="form-control"/>
                </div>
            
                <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <g:form action="list" autocomplete="off" params="[tab: 'filter']">
                <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>
                <pharmacyApp:users userList="${params.list('users')}"/>

                <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block" />
            </g:form>
        </g:else>
    </content>
</g:applyLayout>
