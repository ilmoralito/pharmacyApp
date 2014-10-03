<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
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
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  		<ul class="nav navbar-nav navbar-right">
  			<li class="dropdown ${(controllerName == 'notifications') ? 'active' : ''}">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <g:if test="${notif > 0}">
                <span class="badge notifications">${notif}</span>
              </g:if>
              Notificaciones <span class="caret"></span>
          </a>
          <ul class="dropdown-menu">
              <li>
                <g:link controller="notifications" action="quantity">
                    <g:if test="${q > 0}">
                      <span class="badge notifications">${q}</span>
                    </g:if>
                    Existencias bajas
                </g:link>
              </li>
              <li>
                <g:link controller="notifications" action="expire">
                    <g:if test="${ex > 0}">
                      <span class="badge notifications">${ex}</span>
                    </g:if>
                    Por vencerse
                </g:link>
              </li>
              <li>
                <g:link controller="notifications" action="expired">
                    <g:if test="${exd > 0}">
                      <span class="badge notifications">${exd}</span>
                    </g:if>
                    Vencidos
                </g:link>
              </li>
              <li>
                <g:link controller="notifications" action="expired">
                    <g:if test="${pen > 0}">
                      <span class="badge notifications">${exd}</span>
                    </g:if>
                    Vencidos
                </g:link>
              </li>
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
	</div>
</nav>