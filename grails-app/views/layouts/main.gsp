<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><g:layoutTitle default="Farmacia Santa Barbara"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<r:layoutResources/>
</head>
<body>
	<!--NAVBAR-->
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
	<div class="container">
		<!--SIDEBAR-->
		<div class="col-md-2">
			<ul class="nav nav-pills nav-stacked">
  			<li class="${controllerName == 'provider' ? 'active' : ''}">
  				<g:link controller="provider" action="list">Proveedores</g:link>
  			</li>
  			<li class="${controllerName == 'client' ? 'active' : ''}">
  				<g:link controller="client" action="list">Clientes</g:link>
  			</li>
  			<li class="${controllerName == 'purchaseOrder' ? 'active' : ''}">
  				<g:link controller="purchaseOrder">Pedidos</g:link>
  			</li>
  			<li class="${controllerName == 'sale' ? 'active' : ''}">
  				<g:link controller="sale">Ventas</g:link>
  			</li>
			</ul>
		</div>

		<!--MAIN-->
		<div class="col-md-10">
			<g:layoutBody/>
			<g:if test="${flash.message}">
				<br>${flash.message}
			</g:if>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>