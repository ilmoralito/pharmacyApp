<g:applyLayout name="threeColumns">
    <head>
        <title>Usuarios</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <table class="table table-hover table-striped">
            <colgroup>
               <col span="1" style="width: 45%;">
               <col span="1" style="width: 45%;">
               <col span="1" style="width: 5%;">
               <col span="1" style="width: 5%;">
            </colgroup>
            <thead>
                <th>Nombre</th>
                <th>Email</th>
                <th>Telefono</th>
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
                        <td>${user.email}</td>
                        <td>${user.telephoneNumber}</td>
                        <td>${user.getAuthorities().authority.join(", ").tokenize("_")[1]}</td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </content>

    <content tag="col1">
        <g:form controller="user" action="list" autocomplete="off">
            <g:render template="form"/>

            <input type="submit" class="btn btn-primary btn-block" value="Agregar"/>
        </g:form>
    </content>
</g:applyLayout>
