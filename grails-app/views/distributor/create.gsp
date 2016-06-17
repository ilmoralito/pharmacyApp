<g:applyLayout name="twoColumns">
    <head>
        <title>Distribuidor</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form name="createDistributorForm" action="create" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="sendDistributorForm" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
