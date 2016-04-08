<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, purchaseOrder"/>
    </head>

    <content tag="main">
        <g:form autocomplete="off" class="form-inline">
            <div class="form-group">
                <g:select
                    name="q"
                    from="${products}"
                    optionKey="name"
                    optionValue="name"
                    value="${q}"
                    class="form-control"/>
            </div>

            <g:submitButton name="query" value="Consultar" class="btn btn-primary"/>
        </g:form>
        <br>

        <g:if test="${result}">
            <g:each in="${result}" var="r">
                <g:form autocomplete="off">
                    <p>${r.provider}</p>

                    <g:hiddenField name="product" value="${r.id}"/>

                    <g:if test="${r instanceof ni.sb.Medicine}">
                        <div class="row">
                            <div class="col-md-6">
                                <pharmacyApp:combo
                                    name="presentation"
                                    from="${r.presentations}"
                                    data="measures"/>
                            </div>

                            <div class="col-md-6">
                                <pharmacyApp:combo
                                    name="measure"
                                    from="${r.presentations[0].measures}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <g:textField
                                name="dueDate"
                                class="form-control"
                                placeholder="Fecha de vencimiento"/>
                        </div>
                    </g:if>

                    <g:if test="${r instanceof ni.sb.BrandProduct}">
                        <div class="row">
                            <div class="col-md-6">
                                <pharmacyApp:combo
                                    name="brand"
                                    from="${r.brands}"
                                    data="details"/>
                            </div>

                            <div class="col-md-6">
                                <pharmacyApp:combo
                                    name="detail"
                                    from="${r.brands[0].details}"/>
                            </div>
                        </div>
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

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <input
                                    type="text"
                                    name="purchasePrice"
                                    id="purchasePrice"
                                    min="1"
                                    class="form-control"
                                    placeholder="Precio de compra">
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group">
                                <input
                                    type="text"
                                    name="sellingPrice"
                                    id="sellingPrice"
                                    min="1"
                                    class="form-control"
                                    placeholder="Precio de venta">
                            </div>
                        </div>
                    </div>

                    <g:submitButton name="${submitName}" value="Agregar" class="btn btn-primary"/>
                </g:form>
                <br>
            </g:each>
        </g:if>

        <g:if test="${items}">
            <table class="table table -hover table-striped">
                <colgroup>
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 9.9%;">
                    <col span="1" style="width: 1%;">
                </colgroup>
                <thead>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>P. Compra</th>
                    <th>P. Venta</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th>T. Compra</th>
                    <th>T. Venta</th>
                    <th></th>
                </thead>
                <tbody>
                    <g:each in="${items.sort { it?.product?.name }}" var="item" status="index">
                        <tr>
                            <td>${item?.product?.name} ${item.class}</td>
                            <td>${item.quantity}</td>
                            <td>${item.purchasePrice}</td>
                            <td>${item.sellingPrice}</td>
                            <g:if test="${item instanceof ni.sb.Item}">
                                <td>...</td>
                                <td>+++</td>
                                <td>***</td>
                            </g:if>
                            <g:if test="${item instanceof ni.sb.MedicineOrder}">
                                <td>${item.presentation}</td>
                                <td>${item.measure}</td>
                                <td>${item.dueDate.format("yyyy-MM-dd")}</td>
                            </g:if>
                            <g:if test="${item instanceof ni.sb.BrandProductOrder}">
                                <td>${item.brand}</td>
                                <td>${item.detail}</td>
                                <td></td>
                            </g:if>
                            <td>${item.purchasePrice * item.quantity}</td>
                            <td>${item.sellingPrice * item.quantity}</td>
                            <td>
                                <g:link event="deleteItem" params="[id: item?.product?.id]">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

        </g:if>
    </content>
    <content tag="col1">
        <div id="meta">
            <label>Numero de factura</label>
            <p>${invoiceNumber}</p>
            
            <label>Realizado por</label>
            <P>${applicationContext.springSecurityService.currentUser?.fullName}</P>
            
            <label>Distribuidor</label>
            <P>${distributor.name}</P>
            
            <label>Tipo de pago</label>
            <p><pharmacyApp:paymentType type="${paymentType}"/></p>
            
            <g:if test="${paymentDate}">
                <label>Fecha de pago</label>
                <p>${paymentDate?.format("yyyy-MM-dd")}</p>
            </g:if>
            
            <g:link event="show" class="btn btn-default btn-block">
                Editar
            </g:link>
        </div>
        <br>

        <g:if test="${items}">
            <g:link event="confirm" class="btn btn-primary btn-block btn-lg">
                Confirmar compra
            </g:link>
        </g:if>
    </content>
</g:applyLayout>
