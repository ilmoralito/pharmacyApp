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
            <input
                type="text"
                class="form-control"
                name="j_username"
                id="username"
                placeholder="Correo electrónico"/>
        </div>

        <div class="form-group">
            <input
                type="password"
                class="form-control"
                name="j_password"
                id="password"
                placeholder="Contraseña"/>
        </div>

        <g:submitButton name="send" value="Iniciar sesion" class="btn btn-primary"/>
        <g:link controller="register" action="forgotPassword" class="btn btn-default pull-right">
            Olvido su contraseña
        </g:link>
    </form>
</body>
</html>