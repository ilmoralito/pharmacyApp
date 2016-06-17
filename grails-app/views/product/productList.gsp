<g:applyLayout name="threeColumns">
    <head>
        <title>Productos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>
        <g:render template="list" model="[collection: products, label: 'Productos']"/>
    </content>

    <content tag="col1">
        <g:render template="panel"/>
    </content>
</g:applyLayout>
