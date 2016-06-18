<li class="dropdown ${controllerName == 'user' ? 'active' : ''}">
    <g:set var="fullName" value="${applicationContext.springSecurityService.currentUser?.fullName}"/>
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        ${fullName}
        <span class="caret"></span>
    </a>

    <ul class="dropdown-menu" role="menu">
        <li class="${actionName == 'profile' || actionName == 'password' ? 'active' : ''}">
            <g:link controller="user" action="profile">
                Perfil
            </g:link>
        </li>
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <li class="${controllerName == 'user' && actionName == 'list' ? 'active' : ''}">
                <g:link controller="user" params="[enabled: true]">
                    Administrar usuarios
                </g:link>
            </li>
        </sec:ifAllGranted>

        <li class="divider"></li>
        <li><g:link controller="logout" action="index">Salir</g:link></li>
    </ul>
</li>
