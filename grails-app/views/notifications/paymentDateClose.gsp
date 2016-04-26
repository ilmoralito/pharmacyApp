<g:applyLayout name="twoColumns">
    <head>
        <title>Fecha de pago proxima</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${purchaseOrders}">
            <p>Fecha de pago proxima</p>

            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 70%;">
                </colgroup>
                <thead>
                    <th>Factura</th>
                    <th>Distribuidor</th>
                    <th>Dias para vencerse</th>
                </thead>
                <tbody>
                    <g:each in="${purchaseOrders}" var="purchaseOrder">
                        <tr>
                            <td>
                                <g:link controller="purchaseOrder" action="show" id="${purchaseOrder.id}">
                                    ${purchaseOrder.invoiceNumber}
                                </g:link>
                            </td>
                            <td>${purchaseOrder.distributor.name}</td>
                            <td>${purchaseOrder.paymentDate - new Date()}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
