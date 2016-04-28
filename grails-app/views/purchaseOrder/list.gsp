<g:applyLayout name="threeColumns">
    <head>
        <title>Ordenes de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:if test="${purchaseOrders}">
            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 99%;">
                </colgroup>
                <thead>
                    <th>#</th>
                    <th>Factura</th>
                </thead>
                <tbody>
                    <g:each in="${purchaseOrders}" var="purchaseOrder" status="idx">
                        <tr>
                            <td>${idx + 1}</td>
                            <td>
                                <g:link action="show" params="[id: purchaseOrder.id]">
                                    ${purchaseOrder.invoiceNumber}
                                </g:link>
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
        <g:link action="create" class="btn btn-primary btn-block">
            Crear pedido
        </g:link>

        <br>

        <g:form action="list" autocomplete="off">
            <div class="form-group">
                <g:textField
                    name="invoiceNumber"
                    value="${params?.invoiceNumber}"
                    class="form-control"
                    placeholder="Numero de factura"/>
            </div>

            <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>

            <pharmacyApp:dealers type="checkbox" dealerList="${params.list('distributor')}"/>

            <pharmacyApp:paymentTypeBox type="checkbox" paymentType="${params.list('paymentType')}"/>

            <pharmacyApp:users userList="${params.list('users')}"/>

            <pharmacyApp:paymentStatus paymentStatusList="${params.list('paymentStatus')}"/>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
