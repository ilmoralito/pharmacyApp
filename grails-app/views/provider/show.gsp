<g:applyLayout name="twoColumns">
    <head>
        <title>Proveedores</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-4">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Actualizar" class="btn btn-primary"/>
                </g:form>

                <g:render template="/layouts/errorsMessage" model="[instance:provider]"/>
            </div>
        </div>
    </content>
</g:applyLayout>
