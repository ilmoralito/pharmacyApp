<g:applyLayout name="threeColumns">
    <head>
        <title>Ordenes de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:if test="${purchaseOrders}">
            <table class="table table-hover">
                <caption>${purchaseOrders.size()} resultados</caption>
                <colgroup>
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 17%;">
                    <col span="1" style="width: 55%;">
                </colgroup>
                <thead>
                    <th>Factura</th>
                    <th>Distribuidor</th>
                    <th>Tipo de pago</th>
                    <th>Estado del credito</th>
                    <th>Dias para pagar</th>
                </thead>
                <tbody>
                    <g:each in="${purchaseOrders}" var="po">
                        <tr>
                            <td>
                                <g:link action="show" params="[id: po.id]">
                                    <g:fieldValue bean="${po}" field="invoiceNumber"/>
                                </g:link>
                            </td>
                            <td><g:fieldValue bean="${po}" field="distributor.name"/></td>
                            <td><pharmacyApp:paymentType type="${po.paymentType}"/></td>
                            <td>
                                <g:if test="${po.paymentType == 'credit'}">
                                    <pharmacyApp:purchaseOrderStatus status="${po.paymentStatus}"/>
                                </g:if>
                            </td>
                            <td>
                                <g:if test="${po.paymentType == 'credit'}">
                                    ${po.paymentDate - new Date()}
                                </g:if>
                            </td>
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
        <ul class="nav nav-tabs">
            <li class="${!params.tab || params.tab != 'filter' ? 'active' : ''}">
                <g:link action="list" >Crear</g:link>
            </li>
            <li class="${params.tab == 'filter' ? 'active' : ''}">
                <g:link action="list" params="[tab: 'filter']">Filtrar</g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'filter'}">
            <g:link action="create" class="btn btn-primary btn-block">
                Crear pedido
            </g:link>
        </g:if>

        <g:if test="${params.tab == 'filter'}">
            <g:form name="filterForm" action="list" params="[tab: 'filter']" autocomplete="off">
                <div class="form-group">
                    <label for="invoiceNumber">Numero de factura</label>
                    <g:textField
                        name="invoiceNumber"
                        value="${params?.invoiceNumber}"
                        class="form-control"/>
                </div>

                <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>

                <pharmacyApp:dealers type="checkbox" dealerList="${params.list('distributor')}"/>

                <pharmacyApp:paymentTypeBox type="checkbox" paymentType="${params.list('paymentType')}"/>

                <pharmacyApp:paymentStatusBox type="checkbox" paymentStatusList="${params.list('paymentStatus')}"/>

                <pharmacyApp:users userList="${params.list('users')}"/>

                <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
    </content>
</g:applyLayout>
