<html>
    <head>
        <title><g:message code="spring.security.ui.resetPassword.title"/></title>
        <meta name="layout" content="public"/>
    </head>

    <body>
        <g:form action="resetPassword" name="resetPasswordForm" autocomplete="off">
            <g:hiddenField name="t" value="${token}"/>
            <h4>Escriba su nueva contraseña</h4>

            <div class="form-group">
                <g:passwordField
                    class="form-control"
                    name="password"
                    placeholder="Nueva contraseña"
                    value="${command?.password}"
                    bean="${command}"
                    autofocus="true"/>
            </div>

            <div class="form-group">
                <g:passwordField
                    class="form-control"
                    name="password2"
                    placeholder="Repita su contraseña"
                    value="${command?.password2}"
                    bean="${command}"
                    labelCodeDefault="Password"/>
            </div>

            <input type="submit" id="submit" class="btn btn-primary" value="Actualizar Contraseña"/>
            <g:link controller="login" class="btn btn-default pull-right">Regresar</g:link>
        </g:form>
    </body>
</html>