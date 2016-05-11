<g:applyLayout name="threeColumns">
    <head>
        <title>Gasto diario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:if test="${expenses}">
            <p>Gasto diario</p>
            <table class="table table-hover">
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
                            <td>${expense.user}</td>
                            <td>${expense.description}</td>
                            <td>${expense.quantity}</td>
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

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane ${!filtered ? 'active' : ''}" id="create">
                <g:form action="create" autocomplete="off">
                    <div class="form-group">
                        <label for="description">Motivo del gasto</label>
                        <g:textArea
                            name="description"
                            class="form-control"/>
                    </div>
                
                    <div class="form-group">
                        <label for="quantity">Monto</label>
                        <g:textField name="quantity" placeholder="Monto" class="form-control"/>
                    </div>
                
                    <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
                </g:form>
            </div>

            <div role="tabpanel" class="tab-pane ${filtered ? 'active' : ''}" id="filter">
                <g:form action="list" autocomplete="off">
                    <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>
                    <pharmacyApp:users userList="${params.list('users')}"/>

                    <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block" />
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
