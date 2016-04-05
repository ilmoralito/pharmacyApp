<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:form autocomplete="off">
            <div class="form-group">
                <g:textField
                    name="q"
                    value="${params?.q}"
                    class="form-control"
                    autofocus="true"
                    placeholder="Criterio de busqueda"/>
            </div>

            <g:submitButton name="sendQuery" value="Consultar" class="btn btn-primary"/>
        </g:form>

        <g:if test="${products}">
            ${products}
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
