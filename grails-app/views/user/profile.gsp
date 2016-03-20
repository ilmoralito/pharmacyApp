<g:applyLayout name="twoColumns">
    <head>
        <title>Perfil</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <div class="row">
            <div class="col-md-6">
                <g:form action="profile">
                    <div class="form-group">
                        <g:textField
                            name="username"
                            value="${user?.username}"
                            class="form-control"
                            placeholder="Nombre de usuario"
                            autofocus="true"/>
                    </div>

                    <div class="form-group">
                        <g:textField
                            name="email"
                            value="${user?.email}"
                            class="form-control"
                            placeholder="Email"
                            autofocus="true"/>
                    </div>

                    <div class="form-group">
                        <g:textField
                            name="fullName"
                            class="form-control"
                            value="${user?.fullName}"
                            placeholder="Nombre completo"/>
                    </div>

                    <input type="submit" class="btn btn-primary" value="Confirmar"/>
                </g:form>
            </div>
        </div>

    </content>
</g:applyLayout>
