<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="public">
        <title>Validar clave</title>
        <r:require modules="bootstrap-css, app"/>
    </head>
    <body>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit.</p>

        <g:form action="validate">
            <div class="form-group">
                <g:passwordField
                    name="newPassword"
                    class="form-control"
                    placeholder="Nueva clave"/>
            </div>

            <div class="form-group">
                <g:passwordField
                    name="repeatNewPassword"
                    class="form-control"
                    placeholder="Repetir nueva clave"/>
            </div>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>
    </body>
</html>