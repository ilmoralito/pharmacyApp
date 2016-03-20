<g:applyLayout name="twoColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, addTelefone"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-4">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="confirm" value="Actualizar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
