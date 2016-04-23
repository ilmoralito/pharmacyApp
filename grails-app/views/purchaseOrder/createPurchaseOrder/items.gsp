<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, purchaseOrder"/>
    </head>

    <content tag="main">
        <g:if test="${items}">
            <p href="#" class="trigger">Productos</p>

            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>PRODUCTO</th>
                    <th>CANTIDAD</th>
                    <th>COMPRA</th>
                    <th>VENTA</th>
                    <th>TCOMPRA</th>
                    <th>TVENTA</th>
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
                                <td>${item.purchasePrice * item.quantity}</td>
                                <td>${item.sellingPrice * item.quantity}</td>
                                <td>
                                    <g:link event="deleteItem" params="[id: item?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                    <tr>
                        <td><b>Total</b></td>
                        <td>${items.quantity.sum()}</td>
                        <td colspan="2"></td>
                        <td><pharmacyApp:calcTotal items="${items}" property="purchasePrice"/></td>
                        <td><pharmacyApp:calcTotal items="${items}" property="sellingPrice"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </g:if>

        <g:if test="${medicineOrders}">
            <p href="#" class="trigger">Medicinas</p>

            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 14.2%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>PRODUCTO</th>
                    <th>CANTIDAD</th>
                    <th>COMPRA</th>
                    <th>VENTA</th>
                    <th>F.V.</th>
                    <th>PRESENTACION</th>
                    <th>MEDIDA</th>
                    <th>TCOMPRA</th>
                    <th>TVENTA</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${medicineOrders.groupBy { it.product.provider }.sort { it.key.name }}" var="m">
                        <tr>
                            <td colspan="9">${m.key}</td>
                        </tr>
                        <g:each in="${m.value.sort { it.product.name }}" var="medicine">
                            <tr>
                                <td>${medicine?.product?.name}</td>
                                <td>${medicine.quantity}</td>
                                <td>${medicine.purchasePrice}</td>
                                <td>${medicine.sellingPrice}</td>
                                <td>${medicine.dueDate.format("yyyy-MM-dd")}</td>
                                <td>${medicine.presentation}</td>
                                <td>${medicine.measure}</td>
                                <td>${medicine.purchasePrice * medicine.quantity}</td>
                                <td>${medicine.sellingPrice * medicine.quantity}</td>
                                <td>
                                    <g:link event="deleteMedicineOrder" params="[id: medicine?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                    <tr>
                        <td><b>Total</b></td>
                        <td>${medicineOrders.quantity.sum()}</td>
                        <td colspan="5"></td>
                        <td><pharmacyApp:calcTotal items="${medicineOrders}" property="purchasePrice"/></td>
                        <td><pharmacyApp:calcTotal items="${medicineOrders}" property="sellingPrice"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </g:if>

        <g:if test="${brandProductOrders}">
            <p href="#" class="trigger">Marcas</p>

            <table class="table table-hover" style="margin-top: 10px;">
                <colgroup>
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 16.6%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>PRODUCTO</th>
                    <th>CANTIDAD</th>
                    <th>COMPRA</th>
                    <th>VENTA</th>
                    <th>MARCA</th>
                    <th>DETALLE</th>
                    <th>TCOMPRA</th>
                    <th>TVENTA</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${brandProductOrders.groupBy { it.product.provider }.sort { it.key.name }}" var="b">
                        <tr>
                            <td colspan="9">${b.key}</td>
                        </tr>
                        <g:each in="${b.value.sort { it.product.name }}" var="brand">
                            <tr>
                                <td>${brand?.product?.name}</td>
                                <td>${brand.quantity}</td>
                                <td>${brand.purchasePrice}</td>
                                <td>${brand.sellingPrice}</td>
                                <td>${brand.brand}</td>
                                <td>${brand.detail}</td>
                                <td>${brand.purchasePrice * brand.quantity}</td>
                                <td>${brand.sellingPrice * brand.quantity}</td>
                                <td>
                                    <g:link event="deleteBrandProductOrder" params="[id: brand?.product?.id]">
                                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    </g:link>
                                </td>
                            </tr>
                        </g:each>
                    </g:each>
                    <tr>
                        <td><b>TOTAL</b></td>
                        <td>${brandProductOrders.quantity.sum()}</td>
                        <td colspan="4"></td>
                        <td><pharmacyApp:calcTotal items="${brandProductOrders}" property="purchasePrice"/></td>
                        <td><pharmacyApp:calcTotal items="${brandProductOrders}" property="sellingPrice"/></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </g:if>
    </content>
    <content tag="col1">
        <p>
            <a href="#" id="toggleMeta">
                Metadata
            </a>
        </p>
        <div id="meta" class="panel panel-info hide">
            <table class="table">
                <tbody>
                    <tr>
                        <td><small>Factura</small></td>
                        <td><small>${invoiceNumber}</small></td>
                    </tr>
                    <tr>
                        <td><small>Por</small></td>
                        <td><small>${applicationContext.springSecurityService.currentUser?.fullName}</small></td>
                    </tr>
                    <tr>
                        <td><small>Distribuidor</small></td>
                        <td><small>${distributor.name}</small></td>
                    </tr>
                    <tr>
                        <td><small>Tipo de pago</small></td>
                        <td><small><pharmacyApp:paymentType type="${paymentType}"/></small></td>
                    </tr>
                    <g:if test="${paymentDate}">
                        <tr>
                            <td><small>Fecha de pago</small></td>
                            <td><small>${paymentDate?.format("yyyy-MM-dd")}</small></td>
                        </tr>
                    </g:if>
                </tbody>
            </table>

            <div class="panel-body">
                <g:link event="show" class="btn btn-info btn-block">
                    Editar
                </g:link>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
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
            </div>
        </div>

        <g:if test="${result}">
            <div class="panel panel-default">
                <div class="panel-body">
                    <g:each in="${result}" var="r" status="index">
                        <g:form autocomplete="off" style="${index > 0 ? 'margin-top: 10px;' : ''}">
                            <p>
                                ${r.provider}
                            </p>

                            <g:hiddenField name="product.id" value="${r.id}"/>

                            <g:if test="${r instanceof ni.sb.Medicine}">
                                <pharmacyApp:combo
                                    name="presentation.id"
                                    from="${r.presentations}"
                                    data="measures"/>

                                <pharmacyApp:combo
                                    name="measure.id"
                                    from="${r.presentations[0].measures}"/>

                                <div class="form-group">
                                    <g:textField
                                        name="dueDate"
                                        class="form-control"
                                        placeholder="Fecha de vencimiento"/>
                                </div>
                            </g:if>

                            <g:if test="${r instanceof ni.sb.BrandProduct}">
                                <pharmacyApp:combo
                                    name="brand.id"
                                    from="${r.brands}"
                                    data="details"/>

                                <pharmacyApp:combo
                                    name="detail.id"
                                    from="${r.brands[0].details}"/>
                            </g:if>

                            <div class="form-group">
                                <input
                                    type="number"
                                    name="quantity"
                                    id="quantity"
                                    min="1"
                                    class="form-control"
                                    placeholder="Cantidad">
                            </div>

                            <div class="form-group">
                                <input
                                    type="text"
                                    name="purchasePrice"
                                    id="purchasePrice"
                                    min="1"
                                    class="form-control"
                                    placeholder="Precio de compra">
                            </div>

                            <div class="form-group">
                                <input
                                    type="text"
                                    name="sellingPrice"
                                    id="sellingPrice"
                                    min="1"
                                    class="form-control"
                                    placeholder="Precio de venta">
                            </div>

                            <g:submitButton
                                name="${submitName}"
                                value="Agregar"
                                class="btn btn-primary btn-block"/>
                        </g:form>
                    </g:each>
                </div>
            </div>
        </g:if>

        <g:if test="${items || medicineOrders || brandProductOrders}">
            <g:link event="confirm" class="btn btn-warning btn-block">
                Confirmar compra
            </g:link>
        </g:if>
    </content>
</g:applyLayout>
