<g:applyLayout name="twoColumns">
    <head>
        <title>Empresa</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-5">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
