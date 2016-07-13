<g:applyLayout name="twoColumns">
    <head>
        <title>Empresa</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form action="save" autocomplete="off">
            <g:render template="form"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
