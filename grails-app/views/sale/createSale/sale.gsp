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
                    <select name="id" id="id" class="form-control">
                        <g:each in="${items}" var="instance">
                            <option value="${instance.id}" ${instance.id == item?.id ? 'selected' : ''}>
                                ${instance.product.name}
                            </option>
                        </g:each>
                    </select>
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
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 45%;">
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                        <th>Laboratorio</th>
                        <th>Articulo</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>${d.product.provider.name}</td>
                                <td>${d.getFullName()}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${d.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${d.quantity}">
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
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 45%;">
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                        <th>Laboratorio</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>${d.product.provider}</td>
                                <td>${d.getFullName()}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${d.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${d.quantity}">
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
                        <col span="1" style="width: 45%;">
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                        <th>Laboratorio</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th></th>
                    </thead>
                    <tbody>
                        <g:each in="${data}" var="d">
                            <tr>
                                <td>${d.product.provider}</td>
                                <td>${d.product.name}</td>
                                <td>${d.sellingPrice}</td>
                                <g:form>
                                    <td>
                                        <input
                                            style=""
                                            type="number"
                                            name="quantity"
                                            id="quantity"
                                            min="1"
                                            max="${d.quantity}"
                                            class="form-control input-sm"
                                            placeholder="${d.quantity}">
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
        <ul class="nav nav-tabs">
            <li class="${saleType == 'cash' ? 'active' : ''}">
                <g:link event="changeSaleType" params="[saleType: 'cash']">Contado</g:link>
            </li>
            <li class="${saleType == 'credit' ? 'active' : ''}">
                <g:link event="changeSaleType" params="[saleType: 'credit']">Credito</g:link>
            </li>
        </ul>

        <g:if test="${saleType == 'cash'}">
            <a href="#" id="addClient"><small>Agregar cliente</small></a>
            <div class="${clientFormState}">
                <g:form autocomplete="off">
                    <g:render template="/client/form"/>
                    <g:submitButton
                        name="addClient"
                        value="Agregar cliente"
                        class="btn btn-primary btn-block btn-sm"/>
                </g:form>
                <br>
            </div>
        </g:if>

        <g:form autocomplete="off" id="saleForm">
            <g:hiddenField name="balance" value="${saleDetails.total.sum()}"/>

            <g:if test="${saleType == 'cash'}">
                <div class="form-group">
                    <label for="client">Cliente</label>
                    <pharmacyApp:clientsDataList clientID="${clientID}"/>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Monto</label>
                            <div class="form-control">
                                ${saleDetails.total.sum()}
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="change">Cambio</label>
                            <div class="form-control" id="change"></div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="moneyReceived">Recibido</label>
                    <g:textField name="moneyReceived" class="form-control"/>
                </div>
            </g:if>

            <g:if test="${saleType == 'credit'}">
                <div class="form-group">
                    <label for="invoiceNumber">Numero de factura</label>
                    <g:textField name="invoiceNumber" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="company">Empresa</label>
                    <pharmacyApp:companies/>
                </div>

                <div class="form-group">
                    <label for="employee">Empleado</label>
                    <select name="employee.id" id="employee" class="form-control"></select>
                </div>

                <div id="employeeInformation"></div>

                <div class="form-group">
                    <label>Monto</label>
                    <div class="form-control">
                        ${saleDetails.total.sum()}
                    </div>
                </div>
            </g:if>

            <div class="form-group">
                <label for="annotation">Observacion</label>
                <g:textArea name="annotation" row="1" class="form-control"/>
            </div>

            <g:submitButton
                name="${saleType == 'cash' ? 'confirmCashSale' : 'confirmCreditSale'}"
                value="Confirmar"
                class="btn btn-primary btn-block"/>
        </g:form>

        <script id="template" type="x-tmpl-mustache">
            <div class="panel panel-default">
                <div class="panel-body">
                    <label>Cedula</label>
                    <p>{{ identificationcard }}</p>

                    <label>INSS</label>
                    <p>{{ inss }}</p>

                    <label>Saldo de deuda pendiente</label>
                    <p></p>

                    <label>Limite permitido</label>
                    <p>{{ companycreditlimit }}</p>
                </div>
            </div>
        </script>
    </content>
</g:applyLayout>
