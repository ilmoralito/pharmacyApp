<ul class="nav nav-tabs">
    <li class="${actionName == 'profile' ? 'active' : ''}">
        <g:link controller="user" action="profile">Perfil</g:link>
    </li>

    <li class="${actionName == 'password' ? 'active' : ''}">
        <g:link controller="user" action="password">
            Cambiar Contraseña
        </g:link>
    </li>
</ul>
<br>
