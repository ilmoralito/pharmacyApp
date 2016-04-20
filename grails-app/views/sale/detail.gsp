<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:render template="saleDetailsTable" model="[saleDetails: sale.saleDetails]"/>
    </content>
    <content tag="col1">
        <div class="panel panel-default">
            <table class="table table-hover">
                <div class="panel-heading">Datos de venta</div>
                <tbody>
                    <tr>
                        <td><strong>Hora</strong></td>
                        <td>${sale.dateCreated.format('HH:mm')}</td>
                    </tr>
                    <tr>
                        <td><strong>Saldo</strong></td>
                        <td>${sale.balance}</td>
                    </tr>
                    <tr>
                        <td><strong>Recivido</strong></td>
                        <td>${sale.moneyReceived}</td>
                    </tr>
                    <tr>
                        <td><strong>Vuelto</strong></td>
                        <td>${sale.moneyReceived - sale.balance}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><strong>Vendido por</strong></td>
                    </tr>
                    <tr>
                        <td colspan="2">${sale.user.fullName}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><strong>A nombre de</strong></td>
                    </tr>
                    <tr>
                        <td colspan="2">${sale.client.fullName}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><strong>Observacion</strong></td>
                    </tr>
                    <tr>
                        <td colspan="2">${sale.annotation}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </content>
</g:applyLayout>
