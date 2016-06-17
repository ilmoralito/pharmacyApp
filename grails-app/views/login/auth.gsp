<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="layout" content="public">
    <title>Iniciar sesion</title>
    <r:require modules="bootstrap-css, app"/>
</head>
<body>
    <form action="${postUrl}" method="POST" class="pagination-centered" autocomplete="off" role="form">
        <div class="form-group">
            <label for="username">Email</label>
            <input
                type="text"
                class="form-control input-lg"
                name="j_username"
                id="username"/>
        </div>

        <div class="form-group">
            <label for="password">Contraseña</label>
            <input
                type="password"
                class="form-control input-lg"
                name="j_password"
                id="password"/>
        </div>

        <g:submitButton name="send" value="Iniciar sesion" class="btn btn-primary btn-lg"/>
        <g:link controller="register" action="forgotPassword" class="btn btn-default btn-lg pull-right">
            Olvido su contraseña
        </g:link>
    </form>
</body>
</html>