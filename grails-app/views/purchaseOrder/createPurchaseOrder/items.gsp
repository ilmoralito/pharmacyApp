<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:form autocomplete="off" class="form-inline">
            <div class="form-group">
                <g:select
                    name="q"
                    from="${products}"
                    value="${params?.q}"
                    class="form-control"/>
            </div>

            <g:submitButton name="query" value="Consultar" class="btn btn-primary"/>
        </g:form>
        <br>

        <g:if test="${result}">
            <g:each in="${result}" var="r">
                <g:form autocomplete="off">
                    <p>${r.provider}</p>

                    <g:if test="${r instanceof ni.sb.Medicine}">
                        <div class="form-group">
                            <g:select
                                name="presentation"
                                from="${r.presentations}"
                                class="form-control"/>
                        </div>

                        <div class="form-group">
                            <g:select
                                name="measure"
                                from="${r.presentations*.measures}"
                                class="form-control"/>
                        </div>
                    </g:if>

                    <g:if test="${r instanceof ni.sb.BrandProduct}">
                        <div class="form-group">
                            <g:select
                                name="brand"
                                from="${r.brands}"
                                class="form-control"/>
                        </div>

                        <div class="form-group">
                            <g:select
                                name="detail"
                                from="${r.brands*.details}"
                                class="form-control"/>
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

                    <g:submitButton name="send" value="Agregar" class="btn btn-primary"/>
                </g:form>
                <br>
            </g:each>
        </g:if>
    </content>
    <content tag="col1">
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
    </content>
</g:applyLayout>
