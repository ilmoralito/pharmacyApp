<g:applyLayout name="twoColumns">
    <head>
        <title>Abono</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:link action="index" params="[creditSaleId: payment.creditSale.id]">Regresar</g:link>

        <table class="table table-hover">
            <caption>Detalle de abono</caption>
            <colgroup>
                <col span="1" style="width: 30%;">
                <col span="1" style="width: 70%;">
            </colgroup>
            <tbody>
                <tr>
                    <td>Recibo</td>
                    <td><g:fieldValue bean="${payment}" field="receiptNumber"/></td>
                </tr>
                <tr>
                    <td>Cantidad abonada</td>
                    <td><g:fieldValue bean="${payment}" field="amount"/></td>
                </tr>
                <tr>
                    <td>Referencia</td>
                    <td><g:fieldValue bean="${payment}" field="reference"/></td>
                </tr>
                <tr>
                    <td>Abono realizado por</td>
                    <td><g:fieldValue bean="${payment}" field="madeBy"/></td>
                </tr>
                <tr>
                    <td>Numero de cedula de persona que abono</td>
                    <td><g:fieldValue bean="${payment}" field="madeByIdentityCard"/></td>
                </tr>
                <tr>
                    <td>Atendido por</td>
                    <td><g:fieldValue bean="${payment}" field="attendedBy.fullName"/></td>
                </tr>
                <tr>
                    <td>Fecha de abono</td>
                    <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${payment.dateCreated}"/></td>
                </tr>
            </tbody>
        </table>
    </content>
</g:applyLayout>