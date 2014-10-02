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
	<g:render template="/layouts/navbar"/>
	<div class="container">
		<!--SIDEBAR-->
		<div class="col-md-2">
			<g:render template="/layouts/sidebar"/>
		</div>
		<!--MAIN-->
		<div class="col-md-10">
			<g:layoutBody/>
			<g:if test="${flash.message}">
				<div class="message">${flash.message}</div>
			</g:if>
		</div>
	</div>
	<br>
	<r:layoutResources/>
</body>
</html>