<g:applyLayout name="threeColumns">
    <head>
        <title>Ordenes de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:if test="${purchaseOrders}">
            <table class="table table-hover table-striped">
                <thead>
                    <th># Factura</th>
                </thead>
                <tbody>
                    <g:each in="${purchaseOrders}" var="purchaseOrder">
                        <tr>
                            <td>${purchaseOrder.invoiceNumber}</td>
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
            <p>Numero de factura</p>
            <div class="form-group">
                <g:textField
                    name="invoiceNumber"
                    value="${params?.invoiceNumber}"
                    class="form-control"
                    placeholder="####"/>
            </div>

            <pharmacyApp:fromTo/>

            <pharmacyApp:dealers type="checkbox"/>

            <pharmacyApp:paymentTypeBox type="checkbox"/>

            <pharmacyApp:users/>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
