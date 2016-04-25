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
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown ${controllerName == 'notifications' ? 'active' : ''}">
                    <a
                        href="#"
                        class="dropdown-toggle"
                        data-toggle="dropdown">
                        <span
                            class="glyphicon glyphicon-bell ${session?.lowStocks || session?.aboutToExpire ? 'notify' : ''}"
                            aria-hidden="true">
                        </span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li role="presentation" class="dropdown-header">Articulos</li>
                        <li class="${actionName == 'lowStocks' ? 'active' : ''}">
                            <g:link controller="notifications" action="lowStocks">
                                Existencias bajas
                                <g:if test="${session?.lowStocks}">
                                    <span class="badge">${session?.lowStocks}</span>
                                </g:if>
                            </g:link>
                        </li>
                        <li class="${actionName == 'aboutToExpire' ? 'active' : ''}">
                            <g:link controller="notifications" action="aboutToExpire">
                                Por vencerse
                                <g:if test="${session?.aboutToExpire}">
                                    <span class="badge">${session?.aboutToExpire}</span>
                                </g:if>
                            </g:link>
                        </li>
                        <li role="presentation" class="dropdown-header">Pedidos</li>
                        <li class="${actionName == 'paymentDateClose' ? 'active' : ''}">
                            <g:link controller="notifications" action="paymentDateClose">
                                Fecha de pago proxima
                                <g:if test="${session?.paymentDateClose}">
                                    <span class="badge">${session?.paymentDateClose}</span>
                                </g:if>
                            </g:link>
                        </li>
                    </ul>
                </li>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li class="dropdown ${controllerName == 'report' ? 'active' : ''}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Reportes
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu" role="menu">
                            <li class="${actionName == 'sales' ? 'active' : ''}">
                                <g:link controller="report" action="sales">
                                    Ventas
                                </g:link>
                            </li>
                            <li class="${actionName == 'stock' ? 'active' : ''}">
                                <g:link controller="report" action="stock">
                                    Inventario
                                </g:link>
                            </li>
                            <li class="${actionName == 'clients' ? 'active' : ''}">
                                <g:link controller="report" action="clients">
                                    Clientes
                                </g:link>
                            </li>
                            <li class="${actionName == 'employees' ? 'active' : ''}">
                                <g:link controller="report" action="employees">
                                    A empresas
                                </g:link>
                            </li>
                        </ul>
                    </li>
                </sec:ifAllGranted>
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
            </ul>
        </div>
    </div>
</nav>
