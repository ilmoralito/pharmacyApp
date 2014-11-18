<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><g:layoutTitle default="Welcome"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<r:layoutResources/>
</head>
<body id="background">
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
				<g:if test="${flash.message}">${flash.message}</g:if>
			</div>
		</div>
	</div>
  	<div id="fo">
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