<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Navegar</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link class="navbar-brand" controller="${controllerName}">App name</g:link>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown ${(controllerName == 'notifications') ? 'active' : ''}">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <g:if test="${(session.notif)}">
                            <span class="badge notifications"><span class="glyphicon glyphicon-bell"></span></span>
                        </g:if>
                        Notificaciones <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <g:link controller="notifications" action="quantity">
                                <g:if test="${session.q > 0}">
                                    <span class="badge notifications">${session.q}</span>
                                </g:if>
                                Existencias bajas
                            </g:link>
                        </li>
                    <li>
                        <g:link controller="notifications" action="expire">
                            <g:if test="${session.ex > 0}">
                                <span class="badge notifications">${session.ex}</span>
                            </g:if>
                            Por vencerse
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="notifications" action="expired">
                            <g:if test="${session.exd > 0}">
                                <span class="badge notifications">${session.exd}</span>
                            </g:if>
                            Vencidos
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="notifications" action="pendingOrders">
                            <g:if test="${session.po > 0}">
                                <span class="badge notifications">${session.po}</span>
                            </g:if>
                            Pedidos Pendientes
                        </g:link>
                    </li>
                    <li>
                        <g:link controller="notifications" action="clientPayments">
                            <g:if test="${session.cp > 0}">
                                <span class="badge notifications">${session.cp}</span>
                            </g:if>
                            Pago de clientes
                        </g:link>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <g:set var="fullName" value="${applicationContext.springSecurityService.currentUser?.fullName}"/>

                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                ${fullName}
                <span class="caret"></span></a>

                <ul class="dropdown-menu" role="menu">
                    <li class="${actionName == 'profile' || actionName == 'password' ? 'active' : ''}">
                        <g:link controller="user" action="profile">
                            Perfil
                        </g:link>
                    </li>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li class="${controllerName == 'user' && actionName == 'list' ? 'active' : ''}">
                            <g:link controller="user" action="list">
                                Administrar usuarios
                            </g:link>
                        </li>
                    </sec:ifAllGranted>

                    <li class="divider"></li>

                    <li><g:link controller="logout" action="index">Salir</g:link></li>
                  </ul>
            </li>
          </ul>
    </div>
  </div>
</nav>