<g:applyLayout name="twoColumns">
    <head>
        <title>Marca</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filter"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <p>Marca</p>
                    <g:hiddenField name="id" value="${brand.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
