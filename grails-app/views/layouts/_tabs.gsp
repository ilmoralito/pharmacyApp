<g:if test="${actionName=='profile' || actionName=='updateProfile' || actionName=='password' || actionName=='updatePassword'}">
	<ul class="nav nav-tabs">
	  	<li class="${(actionName == 'profile' || actionName == 'updateProfile') ? 'active' : ''}">
	  		<g:link controller="user" action="profile">Perfil de Usuario</g:link>
	  	</li>

		<li class="${(actionName == 'password' || actionName == 'updatePassword') ? 'active' : ''}">
			<g:link controller="user" action="password">
				Cambiar Contraseña
			</g:link>
		</li>
	</ul>
</g:if>