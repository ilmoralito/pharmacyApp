<g:applyLayout name="twoColumns">
    <head>
        <title>Clave de perfil</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <div class="row">
            <div class="col-md-6">
                <g:form action="password">
                    <div class="form-group">
                        <label for="currentPassword">Clave actual</label>
                        <g:passwordField
                            class="form-control"
                            name="currentPassword"
                            autofocus="true"/>
                    </div>
                
                    <div class="form-group">
                        <label for="newPassword">Nueva clave</label>
                        <g:passwordField
                            class="form-control"
                            name="newPassword"/>
                    </div>
                
                    <div class="form-group">
                        <label for="repeatNewPassword">Repetir nueva clave</label>
                        <g:passwordField
                            class="form-control"
                            name="repeatNewPassword"/>
                    </div>

                    <input type="submit" class="btn btn-primary" value="Confirmar"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
