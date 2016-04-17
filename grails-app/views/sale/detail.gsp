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
                        <td>Hora</td>
                        <td>${sale.dateCreated.format('HH:mm')}</td>
                    </tr>
                    <tr>
                        <td>Realizado por</td>
                        <td>${sale.user.fullName}</td>
                    </tr>
                    <tr>
                        <td>Saldo</td>
                        <td>${sale.balance}</td>
                    </tr>
                    <tr>
                        <td>A nombre de</td>
                        <td>${sale.toName}</td>
                    </tr>
                    <tr>
                        <td>Dinero recivido</td>
                        <td>${sale.moneyReceived}</td>
                    </tr>
                    <tr>
                        <td>Observacion</td>
                    </tr>
                    <tr>
                        <td>${sale.annotation}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </content>
</g:applyLayout>
