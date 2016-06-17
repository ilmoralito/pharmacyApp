<g:applyLayout name="twoColumns">
    <head>
        <title>Distribuidor</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${params?.id}"/>
            <g:render template="form"/>

            <g:submitButton name="update" value="Actualizar" class="btn btn-primary"/>
        </g:form>
    </content>
</g:applyLayout>
