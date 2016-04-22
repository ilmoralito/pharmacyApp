<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, sale"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${items}">
            <g:form>
                <div class="form-group">
                    <g:select
                        name="id"
                        from="${items}"
                        optionKey="id"
                        value="${item?.id}"
                        class="form-control"/>
                </div>
            
                <g:submitButton name="chooseItems" value="Filtrar" class="btn btn-primary"/>
            </g:form>
            <br>
        </g:if>
        <g:else>
            <p>
                No existen articulos registrados
                <g:link controller="purchaseOrder" action="create">realizar un pedido</g:link>
                para continuar
            </p>
        </g:else>

        <g:if test="${data}">
            <table class="table table-hover">
                <g:if test="${item instanceof ni.sb.MedicineOrder}">
                    <colgroup>
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Laboratorio</th>
                        <th>Presentacion</th>
                        <th>Medida</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>
                                    <g:link event="addItem" params="[id: d.id]">
                                        ${d.product.name}
                                    </g:link>
                                </td>
                                <td>${d.product.provider}</td>
                                <td>${d.presentation}</td>
                                <td>${d.measure}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            style=""
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${item.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${item.quantity}">
                                    </td>
                                    <td>
                                        <g:hiddenField name="item.id" value="${d.id}"/>
                                        
                                        <g:submitButton
                                            name="addItem"
                                            value="Agregar"
                                            class="btn btn-default btn-sm pull-right"/>
                                    </td>
                                </g:form>
                            </tr>
                        </g:each>
                    </tbody>
                </g:if>

                <g:if test="${item instanceof ni.sb.BrandProductOrder}">
                    <colgroup>
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                        <col span="1" style="width: 14.28%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Laboratorio</th>
                        <th>Marca</th>
                        <th>Detalle</th>
                        <th>Precio de venta</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>
                                    <g:link event="addItem" params="[id: d.id]">
                                        ${d.product.name}
                                    </g:link>
                                </td>
                                <td>${d.product.provider}</td>
                                <td>${d.brand}</td>
                                <td>${d.detail}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            style=""
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${item.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${item.quantity}">
                                    </td>
                                    <td>
                                        <g:hiddenField name="item.id" value="${d.id}"/>
                                        
                                        <g:submitButton
                                            name="addItem"
                                            value="Agregar"
                                            class="btn btn-default btn-sm pull-right"/>
                                    </td>
                                </g:form>
                            </tr>
                        </g:each>
                    </tbody>
                </g:if>

                <g:if test="${!(item instanceof ni.sb.MedicineOrder) && !(item instanceof ni.sb.BrandProductOrder)}">
                    <colgroup>
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Laboratorio</th>
                        <th>Precio de venta</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>${d.product.name}</td>
                                <td>${d.product.provider}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            style=""
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${item.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${item.quantity}">
                                    </td>
                                    <td>
                                        <g:hiddenField name="item.id" value="${d.id}"/>
                                        
                                        <g:submitButton
                                            name="addItem"
                                            value="Agregar"
                                            class="btn btn-default btn-sm pull-right"/>
                                    </td>
                                </g:form>
                            </tr>
                        </g:each>
                    </tbody>
                </g:if>
            </table>
        </g:if>

        <g:if test="${saleDetails}">
            <g:render template="saleDetailsTable" model="[saleDetails: saleDetails]"/>
        </g:if>
    </content>
    <content tag="col1">
        <a href="#" id="addClient"><small>Agregar cliente</small></a>
        <div class="panel panel-default ${clientFormState}">
            <div class="panel-body">
                <g:form autocomplete="off">
                    <g:render template="/client/form" model="[inputSize: 'input-sm']"/>
                    <g:submitButton
                        name="addClient"
                        value="Confirmar"
                        class="btn btn-primary btn-block btn-sm"/>
                </g:form>
            </div>
        </div>

        <g:form autocomplete="off" id="saleForm">
            <g:hiddenField name="balance" value="${saleDetails.total.sum()}"/>

            <div class="form-group">
                <label for="client">Cliente</label>
                <pharmacyApp:clientsDataList/>
            </div>

            <div class="form-group">
                <label>Total a pagar</label>
                <div class="form-control">
                    ${saleDetails.total.sum()}
                </div>
            </div>

            <div class="form-group">
                <label for="moneyReceived">Recibido</label>
                <g:textField name="moneyReceived" class="form-control"/>
            </div>

            <div class="form-group">
                <label for="change">Cambio</label>
                <div class="form-control" id="change"></div>
            </div>

            <div class="form-group">
                <label for="annotation">Observacion</label>
                <g:textArea name="annotation" class="form-control"/>
            </div>

            <g:submitButton name="confirm" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
