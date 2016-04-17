<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:render template="saleDetailsTable" model="[saleDetails: sale.saleDetails]"/>
    </content>
</g:applyLayout>
