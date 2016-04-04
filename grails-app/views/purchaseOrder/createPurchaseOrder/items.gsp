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
        <P>${}</P>

        <label>Distribuidor</label>
        <P>${distributor}</P>

        <label>Tipo de pago</label>
        <p><pharmacyApp:paymentType type="${paymentType}"/></p>

        <g:if test="${paymentType == 'credit'}">
            <label>Fecha de pago</label>
            <p>${paymentDate}</p>
        </g:if>

        <g:link event="show" class="btn btn-default btn-block">
            Editar
        </g:link>
    </content>
</g:applyLayout>
