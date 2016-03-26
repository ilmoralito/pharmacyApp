<g:applyLayout name="twoColumns">
    <head>
        <title>Producto</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:link
            action="productList"
            params="[providerId: product.provider.id]"
            fragment="${product.id}">
            Regresar
        </g:link>

        <p><br>${product.provider.name}</p>

        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
