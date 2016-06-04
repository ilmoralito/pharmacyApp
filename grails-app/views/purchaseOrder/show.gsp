<g:applyLayout name="threeColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${medicineOrders}">
            <table class="table table-hover">
                <caption>Medicinas</capt>
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
                            <td><g:formatDate format="yyyy-MM-dd" date="${m.dueDate}"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>

        <g:if test="${brandProductOrders}">
            <table class="table table-hover">
                <caption>Productos de marca</caption>
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 50%;">
                </colgroup>
                <thead>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Compra</th>
                    <th>Venta</th>
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
        </g:if>

        <g:if test="${items}">
            <table class="table table-hover">
                <caption>Productos</caption>
                <thead>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Compra</th>
                    <th>Venta</th>
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
        </g:if>
    </content>

    <content tag="col1">
        <ul class="nav nav-tabs">
            <li class="${!params.tab || params.tab != 'task' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id]">
                    Datos
                </g:link>
            </li>
            <li class="${params.tab == 'task' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'task']">
                    Tareas
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'task'}">
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
        </g:if>

        <g:if test="${params.tab == 'task'}">
            <g:form name="updatePurchaseOrderForm" action="update" params="[tab: 'task']" autocomplete="off">
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
        </g:if>
    </content>
</g:applyLayout>
