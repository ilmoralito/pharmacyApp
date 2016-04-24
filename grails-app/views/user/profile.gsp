<g:applyLayout name="twoColumns">
    <head>
        <title>Perfil</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <div class="row">
            <div class="col-md-6">
                <g:form action="profile" autocomplete="off">
                    <g:render template="form"/>

                    <input type="submit" class="btn btn-primary" value="Confirmar"/>
                </g:form>
            </div>
        </div>

    </content>
</g:applyLayout>
