<g:applyLayout name="twoColumns">
    <head>
        <title>Perfil clave</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <div class="row">
            <div class="col-md-6">
                <g:form action="password">
                    <div class="form-group">
                        <g:passwordField
                            class="form-control"
                            name="currentPassword"
                            placeholder="Clave actual"
                            autofocus="true"/>
                    </div>
                
                    <div class="form-group">
                        <g:passwordField
                            class="form-control"
                            name="newPassword"
                            placeholder="Nueva clave"/>
                    </div>
                
                    <div class="form-group">
                        <g:passwordField
                            class="form-control"
                            name="repeatNewPassword"
                            placeholder="Confirme nueva clave"
                           />
                    </div>

                    <input type="submit" class="btn btn-primary" value="Confirmar"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
