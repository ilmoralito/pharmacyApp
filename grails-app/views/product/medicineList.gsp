<g:applyLayout name="threeColumns">
    <head>
        <title>Medicinas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, filter"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>
        <g:render template="list" model="[collection: medicines, label: 'Medicinas']"/>
    </content>

    <content tag="col1">
        <g:render template="panel"/>
    </content>
</g:applyLayout>
