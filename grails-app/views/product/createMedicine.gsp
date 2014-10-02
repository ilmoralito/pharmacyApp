<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Agregar Medicamento</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="navbar"/>

	<g:form action="createMedicine" autocomplete="off">
		<g:hiddenField name="providerId" value="${providerId}"/>
		<g:render template="form"/>
		
		<h4>Presentaciones</h4>
		<g:set var="presentations" value="${grailsApplication.config.ni.sb.presentationsAndMeasures}"/>
		<g:each in="${presentations.keySet()}" var="presentation">
			<p>${presentation}</p>
			<g:each in="${presentations[presentation]}" var="measure">
				<div class="checkbox">
					<label>
						<g:checkBox name="${presentation}" value="${measure}" checked="false"/> ${measure}
					</label>
				</div>
			</g:each>
		</g:each>
		<g:submitButton name="confirm" value="Agregar" class="btn btn-primary"/>
	</g:form>
</body>
</html>
