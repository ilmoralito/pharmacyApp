<g:applyLayout name="twoColumns">
    <head>
        <title>Distribuidor</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${params?.id}"/>
            <div class="row">
                <div class="col-md-5">
                    <g:render template="form"/>
                </div>
            </div>

            <g:submitButton name="update" value="Actualizar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
