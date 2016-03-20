<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <table class="table table-hover">
            <thead>
                <th>Nombre</th>
                <th>Login</th>
                <th>Email</th>
                <th>Rol</th>
            </thead>
            <tbody>
                <g:each in="${users}" var="user">
                    <tr>
                        <td>
                            <g:link action="show" id="${user.id}">
                                ${user.fullName}
                            </g:link>
                        </td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.getAuthorities().authority.join(", ")}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="col1">
        <g:form controller="user" action="list">
            <div class="form-group">
                <g:textField
                    type="text"
                    class="form-control"
                    name="username"
                    placeHolder="Correo electrónico"
                    required=""/>
            </div>
            <div class="form-group">
                <g:textField
                    type="text"
                    class="form-control"
                    name="fullName"
                    placeHolder="Nombre completo"
                    required=""/>
            </div>
            <div class="form-group">
                <g:select
                    name="authority"
                    from="${['Administrador', 'Usuario']}"
                    keys="['ROLE_ADMIN', 'ROLE_USER']"
                    noSelection="[null:'Selecciona rol del usuario']"
                    class="form-control"/>
            </div>

            <input type="submit" class="btn btn-primary" value="Agregar"/>
        </g:form>
    </content>
</g:applyLayout>
