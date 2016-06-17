<g:applyLayout name="threeColumns">
    <head>
        <title>Marcas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, filter"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>
        <g:render template="list" model="[collection: brandProducts, label: 'Marcas']"/>
    </content>

    <content tag="col1">
        <g:render template="panel"/>
    </content>
</g:applyLayout>
