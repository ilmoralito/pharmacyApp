<g:applyLayout name="threeColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
            <g:if test="${medicineOrders}">
                <p>Medicinas</p>
                <table class="table table-hover table-condensed">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 45%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                        <th>Medicina</th>
                        <th>Cantidad</th>
                        <th>Compra</th>
                        <th>Venta</th>
                        <th>Vencimiento</th>
                    </thead>
                    <tbody>
                        <g:each in="${medicineOrders}" var="m">
                            <tr>
                                <td>
                                    <g:fieldValue bean="${m}" field="product.name"/>
                                    <g:fieldValue bean="${m}" field="presentation"/>
                                    <g:fieldValue bean="${m}" field="measure"/>
                                </td>
                                <td><g:fieldValue bean="${m}" field="quantity"/></td>
                                <td><g:fieldValue bean="${m}" field="purchasePrice"/></td>
                                <td><g:fieldValue bean="${m}" field="sellingPrice"/></td>
                                <td><g:formatDate format="yyyy-MM-dd" date="dueDate"/></td>
                                <td></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>

            <g:elseif test="${brandProductOrders}">
                <p>Productos de marca</p>
                <table class="table table-hover table-condensed">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 50%;">
                    </colgroup>
                    <thead>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio de compra</th>
                        <th>Precio de venta</th>
                    </thead>
                    <tbody>
                        <g:each in="${brandProductOrders}" var="bp">
                            <tr>
                                <td>
                                    <g:fieldValue bean="${bp}" field="product.name"/>
                                    <g:fieldValue bean="${bp}" field="brand"/>
                                    <g:fieldValue bean="${bp}" field="detail"/>
                                </td>
                                <td><g:fieldValue bean="${bp}" field="quantity"/></td>
                                <td><g:fieldValue bean="${bp}" field="purchasePrice"/></td>
                                <td><g:fieldValue bean="${bp}" field="sellingPrice"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:elseif>

            <g:else>
                <p>Productos</p>
                <table class="table table-hover table-condensed">
                    <thead>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio de compra</th>
                        <th>Precio de venta</th>
                    </thead>
                    <tbody>
                        <g:each in="${items}" var="item">
                            <tr>
                                <td><g:fieldValue bean="${item}" field="product.name"/></td>
                                <td><g:fieldValue bean="${item}" field="quantity"/></td>
                                <td><g:fieldValue bean="${item}" field="purchasePrice"/></td>
                                <td><g:fieldValue bean="${item}" field="sellingPrice"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:else>
    </content>

    <content tag="col1">
        <p>Informacion de pedido</p>

        <label>Distribuidor</label>
        <p><g:fieldValue bean="${purchaseOrder}" field="distributor.name"/></p>

        <label>Factura</label>
        <p><g:fieldValue bean="${purchaseOrder}" field="invoiceNumber"/></p>

        <label>Creado por</label>
        <p><g:fieldValue bean="${purchaseOrder}" field="user.fullName"/></p>

        <label>Creado el</label>
        <p><g:formatDate format="yyyy-MM-dd" date="${purchaseOrder.dateCreated}"/></p>

        <label>Tipo de pago</label>
        <p><pharmacyApp:paymentType type="${purchaseOrder.paymentType}"/></p>

        <g:if test="${purchaseOrder.paymentType == 'credit'}">
            <label>Fecha de pago</label>
            <p><g:formatDate format="yyyy-MM-dd" date="${purchaseOrder.paymentDate}"/></p>

            <label>Estado de pago</label>
            <p><pharmacyApp:purchaseOrderStatus status="${purchaseOrder.paymentStatus}"/></p>
        </g:if>

        <p>Tareas</p>

        <g:form name="task" action="update">
            <g:hiddenField name="id" value="${purchaseOrder.id}"/>

            <div class="form-group" style="margin-bottom: 10px;">
                <label for="invoiceNumber">Numero de factura</label>
                <g:textField
                    name="invoiceNumber"
                    value="${purchaseOrder.invoiceNumber}"
                    class="form-control"
                    placeholder="Numero de factura"/>
            </div>

            <pharmacyApp:paymentTypeBox type="radio" paymentType="${purchaseOrder.paymentType}"/>

            <pharmacyApp:paymentStatusBox type="radio" paymentStatusList="${purchaseOrder.paymentStatus}"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
