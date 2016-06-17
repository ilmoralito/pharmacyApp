<g:applyLayout name="threeColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${medicineOrders}">
            <table class="table table-hover">
                <caption>${medicineOrders.size()} Medicinas</caption>
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 10%;">
                </colgroup>
                <thead>
                    <th>Medicina</th>
                    <th>C. Original</th>
                    <th>C. Actual</th>
                    <th>Compra</th>
                    <th>Venta</th>
                    <th>Vence</th>
                </thead>
                <tbody>
                    <g:each in="${medicineOrders}" var="m">
                        <tr>
                            <td>${m.getFullName()}</td>
                            <td><g:fieldValue bean="${m}" field="fixedQuantity"/></td>
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
                <caption>${brandProductOrders.size()} Productos de marca</caption>
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 11%;">
                    <col span="1" style="width: 19%;">
                </colgroup>
                <thead>
                    <th>Producto</th>
                    <th>C. Original</th>
                    <th>C. Actual</th>
                    <th>Compra</th>
                    <th>Venta</th>
                </thead>
                <tbody>
                    <g:each in="${brandProductOrders}" var="bp">
                        <tr>
                            <td>${bp.getFullName()}</td>
                            <td><g:fieldValue bean="${bp}" field="fixedQuantity"/></td>
                            <td><g:fieldValue bean="${bp}" field="quantity"/></td>
                            <td><g:fieldValue bean="${bp}" field="purchasePrice"/></td>
                            <td><g:fieldValue bean="${bp}" field="sellingPrice"/></td>
                            <td></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>

        <g:if test="${items}">
            <table class="table table-hover">
                <caption>${items.size()} Productos</caption>
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <thead>
                    <th>Producto</th>
                    <th>C. Original</th>
                    <th>C. Actual</th>
                    <th>Compra</th>
                    <th>Venta</th>
                </thead>
                <tbody>
                    <g:each in="${items}" var="item">
                        <tr>
                            <td><g:fieldValue bean="${item}" field="product.name"/></td>
                            <td><g:fieldValue bean="${item}" field="fixedQuantity"/></td>
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
            <li class="${!params.tab || !(params.tab in ['task', 'resume', 'remove']) ? 'active' : ''}">
                <g:link action="show" params="[id: params.id]">
                    <span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
                </g:link>
            </li>
            <li class="${params.tab == 'resume' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'resume']">
                    <span class="glyphicon glyphicon-th" aria-hidden="true"></span>
                </g:link>
            </li>
            <li class="${params.tab == 'task' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'task']">
                    <span class="glyphicon glyphicon-pencil"></span>
                </g:link>
            </li>
            <li class="${params.tab == 'remove' ? 'active' : ''}">
                <g:link action="show" params="[id: params.id, tab: 'remove']">
                    <span class="glyphicon glyphicon-trash"></span>
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || !(params.tab in ['task', 'resume', 'remove'])}">
            <pharmacyApp:purchaseOrderDetail
                distributor="${purchaseOrderDetail.distributor}"
                invoiceNumber="${purchaseOrderDetail.invoiceNumber}"
                paymentType="${purchaseOrderDetail.paymentType}"
                createdBy="${purchaseOrderDetail.createdBy}"
                dateCreated="${purchaseOrderDetail.dateCreated}"
                paymentDate="${purchaseOrderDetail.paymentDate}"
                paymentStatus="${purchaseOrderDetail.paymentStatus}"/>
        </g:if>

        <g:if test="${params.tab == 'resume'}">
            <pharmacyApp:purchaseOrderResume
                label="${medicinesResume.label}"
                products="${medicinesResume.products}"
                totalPurchasePrice="${medicinesResume.totalPurchasePrice}"
                totalSellingPrice="${medicinesResume.totalSellingPrice}"
                totalProfits="${medicinesResume.totalProfits}"/>

            <pharmacyApp:purchaseOrderResume
                label="${brandProductOrdersResume.label}"
                products="${brandProductOrdersResume.products}"
                totalPurchasePrice="${brandProductOrdersResume.totalPurchasePrice}"
                totalSellingPrice="${brandProductOrdersResume.totalSellingPrice}"
                totalProfits="${brandProductOrdersResume.totalProfits}"/>

            <pharmacyApp:purchaseOrderResume
                label="${itemsResume.label}"
                products="${itemsResume.products}"
                totalPurchasePrice="${itemsResume.totalPurchasePrice}"
                totalSellingPrice="${itemsResume.totalSellingPrice}"
                totalProfits="${itemsResume.totalProfits}"/>
        </g:if>

        <g:if test="${params.tab == 'task'}">
            <g:form action="update" params="[tab: 'task']" autocomplete="off">
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

        <g:if test="${params.tab == 'remove'}">
            <g:if test="${soledItems}">
                <p>
                    No es posible eliminar el pedido
                    debido a que los siguientes articulos
                    ya han sido vendidos
                </p>

                <table class="table table-hover">
                    <thead>
                        <th>Articulos afectados</th>
                    </thead>
                    <tbody>
                        <g:each in="${soledItems}" var="item">
                            <tr>
                                <td><g:fieldValue bean="${item}" field="product.name"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
            <g:else>
                <g:link action="delete" params="[id: params.id]" class="btn btn-danger btn-block">
                    Eliminar pedido
                </g:link>

                <br>
                <p>Un pedido se eliminara si no se han vendido ninguno de sus productos.</p>

                <p>De cumplirse la condicion se eliminara el pedido y no podra deshacer los cambios.</p>
            </g:else>
        </g:if>
    </content>
</g:applyLayout>
