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

        <p><br>Filtrar</p>

        <g:form action="list" autocomplete="off">
            <div class="form-group">
                ...
            </div>
        </g:form>
    </content>
</g:applyLayout>
