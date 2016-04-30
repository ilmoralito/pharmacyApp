<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, bootstrap3datepicker, purchaseOrder"/>
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
            <a href="#" id="toggleMeta">Detalle de pedido</a>
        </p>
        <div id="meta" class="hide">
            <label>Factura</label>
            <p>${invoiceNumber}</p>

            <label>Creado por</label>
            <p>${applicationContext.springSecurityService.currentUser?.fullName}</p>

            <label>Distribuidor</label>
            <p>${distributor.name}</p>

            <label>Tipo de pago</label>
            <p><pharmacyApp:paymentType type="${paymentType}"/></small></p>

            <g:if test="${paymentDate}">
                <label>Fecha de pago</label>
                <p>${paymentDate?.format("yyyy-MM-dd")}</p>
            </g:if>

            <p>
                <g:link event="show">Editar</g:link>
            </p>
        </div>

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

                    <p><br>${r.provider}</p>

                    <div class="form-group">
                        <input
                            type="number"
                            name="quantity"
                            id="quantity"
                            min="1"
                            class="form-control"
                            autofocus="true"
                            placeholder="Cantidad">
                    </div>

                    <g:if test="${r instanceof ni.sb.Medicine}">
                        <div class="form-group">
                            <g:textField
                                name="dueDate"
                                class="form-control"
                                placeholder="Fecha de vencimiento"/>
                        </div>

                        <pharmacyApp:combo
                            name="presentation.id"
                            from="${r.presentations}"
                            data="measures"/>

                        <pharmacyApp:combo
                            name="measure.id"
                            from="${r.presentations[0].measures}"/>
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
        </g:if>

        <g:if test="${items || medicineOrders || brandProductOrders}">
            <g:link event="confirm" class="btn btn-warning btn-block">
                Confirmar compra
            </g:link>
        </g:if>
    </content>
</g:applyLayout>
