<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Navegar</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    	<g:link class="navbar-brand" controller="${controllerName}">Farmacia Santa Barbara</g:link>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Notificaciones <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
          <!--TODO-->
        </ul>
      </li>
			<li class="dropdown">
	  		<g:set var="userName" value="${applicationContext.springSecurityService.currentUser.fullName}"/>
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	  		<g:if test="${(userName != null)}">${userName}</g:if>
	  		<g:else><sec:username/></g:else>
        <span class="caret"></span></a>
        <ul class="dropdown-menu" role="menu">
          <li><g:link controller="user" action="profile">Perfil</g:link></li>
          <sec:ifAllGranted roles="ROLE_ADMIN">
          	<li><g:link controller="user" action="list">Administrar usuarios</g:link></li>
          </sec:ifAllGranted>
          <li class="divider"></li>
          <li><g:link controller="logout" action="index">Salir</g:link></li>
        </ul>
      </li>
		</ul>
	</div>
</nav>