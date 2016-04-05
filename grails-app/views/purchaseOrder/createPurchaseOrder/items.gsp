<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        
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
