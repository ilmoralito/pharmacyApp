<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><g:layoutTitle default="Registro de Usuarios"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<r:require modules="bootstrap, app"/>
	<r:layoutResources/>
</head>
<body  id="background">
	<div class="container" id="cont">
	<br><br>
		<div class="row">
			<div class="col-md-4 col-md-offset-2">
				<h1 class="nameFarm">SANTA BARBARA</h1>
				<p class="subName">Farmacia, Mina el Limon.</p>
			</div>
			<div class="col-md-4">
				<g:layoutBody/>
				<br>
				<g:if test="${flash.message}">
					<div class="alert alert-info">
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						${flash.message}
					</div>
				</g:if>
				<g:hasErrors bean="${userInstance}">
					<div class="alert alert-info">
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<g:eachError bean="${userInstance}" var="us">
							<li id="err">
					 	 		<g:message error="${us}"/>
					 		</li>
						</g:eachError>
					</div>
				</g:hasErrors>
			</div>
		</div>
	</div>
  	<div id="footer">
  		<div class="col-md-6">
  			<h5>
  				<a href="mailto:amakenadog@gmail.com" class="contacto">
					<span class="glyphicon glyphicon-envelope"></span>Contacto
				</a>
  			</h5>
  		</div>
		<div class="col-md-6 pull-right">
			<h5 class="pull-right">Copyright © 2014 GPT. Todos los derechos reservados</h5>
  		</div>
	</div>
	<r:layoutResources/>
</body>
</html>
