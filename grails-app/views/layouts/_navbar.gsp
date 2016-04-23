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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <g:link controller="notifications" action="quantity">
                                Existencias bajas
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="notifications" action="expire">
                                Por vencerse
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="notifications" action="expired">
                                Vencidos
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="notifications" action="pendingOrders">
                                Pedidos Pendientes
                            </g:link>
                        </li>
                        <li>
                            <g:link controller="notifications" action="clientPayments">
                                Pago de clientes
                            </g:link>
                        </li>
                    </ul>
                </li>
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
