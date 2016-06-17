<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, purchaseOrder"/>
    </head>

    <content tag="main">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!productTab || !(productTab in ['medicineTab', 'brandProductTab']) ? 'active' : ''}">
                <g:link event="changeProductListTab">
                    Productos
                </g:link>
            </li>
            <li role="presentation" class="${productTab == 'medicineTab' ? 'active' : '' }">
                <g:link event="changeProductListTab" params="[productTab: 'medicineTab']">
                    Medicinas
                </g:link>
            </li>
            <li role="presentation" class="${productTab == 'brandProductTab' ? 'active' : '' }">
                <g:link event="changeProductListTab" params="[productTab: 'brandProductTab']">
                    Productos de marca
                </g:link>
            </li>
        </ul>

        <g:if test="${items && (!productTab || !(productTab in ['medicineTab', 'brandProductTab']))}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Productos</th>
                    <th>Cantidad</th>
                    <th>Precio de compra</th>
                    <th>Precio de venta</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${items.groupBy { it.product.provider }.sort { it.key.name }}" var="p">
                        <tr>
                            <td colspan="9">${p.key}</td>
                        </tr>
                        <g:each in="${p.value.sort { it.product.name }}" var="item">
                            <tr>
                                <td>${item?.product?.name}</td>
                                <td>${item.quantity}</td>
                                <td>${item.purchasePrice}</td>
                                <td>${item.sellingPrice}</td>
                                <td>
                                    <g:link event="deleteItem" class="deleter" params="[id: item?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>

        <g:if test="${medicineOrders && productTab == 'medicineTab'}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Medicinas</th>
                    <th>Cantidad</th>
                    <th>Precio de compra</th>
                    <th>Precio de venta</th>
                    <th>Vence</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${medicineOrders.groupBy { it.product.provider }.sort { it.key.name }}" var="m">
                        <tr>
                            <td colspan="9">${m.key}</td>
                        </tr>
                        <g:each in="${m.value.sort { it.product.name }}" var="medicine">
                            <tr>
                                <td>${medicine.getFullName()}</td>
                                <td>${medicine.quantity}</td>
                                <td>${medicine.purchasePrice}</td>
                                <td>${medicine.sellingPrice}</td>
                                <td>${medicine.dueDate.format("yyyy-MM-dd")}</td>
                                <td>
                                    <g:link event="deleteMedicineOrder" params="[id: medicine?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>

        <g:if test="${brandProductOrders && productTab == 'brandProductTab'}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 35%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Productos</th>
                    <th>Cantidad</th>
                    <th>Precio de compra</th>
                    <th>Precio de venta</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${brandProductOrders.groupBy { it.product.provider }.sort { it.key.name }}" var="b">
                        <tr>
                            <td colspan="9">${b.key}</td>
                        </tr>
                        <g:each in="${b.value.sort { it.product.name }}" var="brand">
                            <tr>
                                <td>${brand.getFullName()}</td>
                                <td>${brand.quantity}</td>
                                <td>${brand.purchasePrice}</td>
                                <td>${brand.sellingPrice}</td>
                                <td>
                                    <g:link event="deleteBrandProductOrder" params="[id: brand?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
    </content>

    <content tag="col1">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!tab || !(tab in ['detail', 'resume', 'send']) ? 'active' : ''}">
                <g:link event="changeItemsTab">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${tab == 'detail' ? 'active' : '' }">
                <g:link event="changeItemsTab" params="[tab: 'detail']">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${tab == 'resume' ? 'active' : ''}">
                <g:link event="changeItemsTab" params="[tab: 'resume']">
                    <span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
                </g:link>
            </li>
            <li role="presentation" class="${tab == 'send' ? 'active' : '' }">
                <g:link event="changeItemsTab" params="[tab: 'send']">
                    <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                </g:link>
            </li>
        </ul>

        <g:if test="${!tab || !(tab in ['detail', 'resume', 'send'])}">
            <g:form autocomplete="off">
                <div class="form-group">
                    <g:select
                        name="q"
                        from="${products}"
                        optionKey="name"
                        optionValue="name"
                        value="${q}"
                        class="form-control"/>
                </div>

                <g:submitButton
                    name="query"
                    value="Consultar"
                    class="btn btn-primary btn-block"/>
            </g:form>

            <g:if test="${result}">
                <g:each in="${result}" var="r">
                    <g:form autocomplete="off" id="item">
                        <g:hiddenField name="product.id" value="${r.id}"/>

                        <p style="margin-top: 10px;">${r.provider}</p>

                        <div class="form-group">
                            <label for="quantity">Cantidad</label>
                            <input
                                type="number"
                                name="quantity"
                                id="quantity"
                                min="1"
                                class="form-control"
                                autofocus="true">
                        </div>

                        <g:if test="${r instanceof ni.sb.Medicine}">
                            <div class="form-group">
                                <label for="dueDate">Fecha de vencimiento</label>
                                <g:textField
                                    name="dueDate"
                                    class="form-control"/>
                            </div>
                        </g:if>

                        <g:if test="${r instanceof ni.sb.Medicine || r instanceof ni.sb.BrandProduct}">
                            <label for="brand.id">${r instanceof ni.sb.Medicine ? 'Presentacion' : 'Marca'}</label>
                            <pharmacyApp:combo
                                name="${r instanceof ni.sb.Medicine ? 'presentation.id' : 'brand.id'}"
                                from="${r instanceof ni.sb.Medicine ? r.presentations : r.brands}"
                                data="${r instanceof ni.sb.Medicine ? 'measures' : 'details'}"/>

                            <label for="detail.id">${r instanceof ni.sb.Medicine ? 'Medida' : 'Detalle'}</label>
                            <pharmacyApp:combo
                                name="${r instanceof ni.sb.Medicine ? 'measure.id' : 'detail.id'}"
                                from="${r instanceof ni.sb.Medicine ? r.presentations[0].measures : r.brands[0].details}"/>
                        </g:if>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="purchasePrice">Precio compra</label>
                                    <input
                                        type="text"
                                        name="purchasePrice"
                                        id="purchasePrice"
                                        min="1"
                                        class="form-control">
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="sellingPrice">Precio venta</label>
                                    <input
                                        type="text"
                                        name="sellingPrice"
                                        id="sellingPrice"
                                        min="1"
                                        class="form-control">
                                </div>
                            </div>
                        </div>

                        <g:submitButton
                            name="${submitName}"
                            value="Agregar"
                            class="btn btn-primary btn-block"/>
                    </g:form>
                </g:each>
            </g:if>
        </g:if>
        <g:elseif test="${tab == 'detail'}">
            <g:form autocomplete="off">
                <g:render template="createPurchaseOrder/form"/>

                <g:submitButton name="updatePurchaseOrder" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:elseif>
        <g:elseif test="${tab == 'resume'}">
            <g:if test="${items}">
                <table class="table table-hover table-condensed">
                    <caption>Productos</caption>
                    <tbody>
                        <tr>
                            <td>Productos</td>
                            <td>${items.size()}</td>
                        </tr>
                        <tr>
                            <td>Total compra</td>
                            <td><pharmacyApp:calcTotal items="${items}" property="purchasePrice"/></td>
                        </tr>
                        <tr>
                            <td>Total venta</td>
                            <td><pharmacyApp:calcTotal items="${items}" property="sellingPrice"/></td>
                        </tr>
                        <tr>
                            <td>Total ganacia</td>
                            <td><pharmacyApp:getProfits items="${items}"/></td>
                        </tr>
                    </tbody>
                </table>
            </g:if>

            <g:if test="${medicineOrders}">
                <table class="table table-hover table-condensed">
                    <caption>Medicinas</caption>
                    <tbody>
                        <tr>
                            <td>Productos</td>
                            <td>${medicineOrders.size()}</td>
                        </tr>
                        <tr>
                            <td>Total compra</td>
                            <td><pharmacyApp:calcTotal items="${medicineOrders}" property="purchasePrice"/></td>
                        </tr>
                        <tr>
                            <td>Total venta</td>
                            <td><pharmacyApp:calcTotal items="${medicineOrders}" property="sellingPrice"/></td>
                        </tr>
                        <tr>
                            <td>Total ganacia</td>
                            <td><pharmacyApp:getProfits items="${medicineOrders}"/></td>
                        </tr>
                    </tbody>
                </table>
            </g:if>

            <g:if test="${brandProductOrders}">
                <table class="table table-hover table-condensed">
                    <caption>Marcas</caption>
                    <tbody>
                        <tr>
                            <td>Productos</td>
                            <td>${brandProductOrders.size()}</td>
                        </tr>
                        <tr>
                            <td>Total compra</td>
                            <td><pharmacyApp:calcTotal items="${brandProductOrders}" property="purchasePrice"/></td>
                        </tr>
                        <tr>
                            <td>Total venta</td>
                            <td><pharmacyApp:calcTotal items="${brandProductOrders}" property="sellingPrice"/></td>
                        </tr>
                        <tr>
                            <td>Total ganacia</td>
                            <td><pharmacyApp:getProfits items="${brandProductOrders}"/></td>
                        </tr>
                    </tbody>
                </table>
            </g:if>
        </g:elseif>
        <g:elseif test="${tab == 'send'}">
            <g:if test="${items || medicineOrders || brandProductOrders}">
                <g:link event="confirm" class="btn btn-danger btn-block">
                    Confirmar compra
                </g:link>
            </g:if>
        </g:elseif>
    </content>
</g:applyLayout>
