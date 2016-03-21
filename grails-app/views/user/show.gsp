<g:applyLayout name="twoColumns">
    <head>
        <title>Usuario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <g:link action="list">Regresar</g:link>
                <br>
                <br>

                <g:form action="update">
                    <g:hiddenField name="id" value="${params?.id}"/>

                    <g:render template="form"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
