<g:applyLayout name="threeColumns">
    <head>
        <title>Articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
            
    </content>
    <content tag="col1">
        <p>Datos de pedido</p>

        <label>Numero de factura</label>
        <p>${invoiceNumber}</p>

        <label>Realizado por</label>
        <P>${currentUser.authorities}</P>

        <label>Distribuidor</label>
        <P>${distributor.name}</P>

        <label>Tipo de pago</label>
        <p><pharmacyApp:paymentType type="${paymentType}"/></p>

        <g:link name="edit" class="btn btn-default btn-block">
            Editar
        </g:link>
    </content>
</g:applyLayout>
