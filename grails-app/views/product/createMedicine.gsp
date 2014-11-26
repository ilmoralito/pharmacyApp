<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Agregar Medicamento</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, productMedicine"/>
</head>
<body>
	<g:render template="toolbar"/>
	<g:render template="navbar"/>
	<div class="row">
		<g:form class="form-inline" action="createMedicine" autocomplete="off">
			<div class="col-md-12">
				<g:hiddenField name="providerId" value="${providerId}"/>
				<g:render template="form"/>
				<g:submitButton name="confirm" value="Agregar" class="btn btn-primary"/>
			</div>
			<div class="col-md-12">
			<h4>Presentaciones</h4>
				<g:set var="cont" value="${1}"/>
				<g:set var="presentations" value="${grailsApplication.config.ni.sb.presentationsAndMeasures}"/>
				<g:each in="${presentations.keySet()}" var="presentation" status="i">
				<g:if test="${cont == 1}">
					<div class="row">
				</g:if>
					<div class="col-md-3">
						<p><a href="#" class="presentation">${presentation}</a></p>
						<div class="measures">
							<g:each in="${presentations[presentation]}" var="measure">
								<div class="checkbox">
									<label>
										<g:checkBox name="${presentation}" value="${measure}" checked="false"/> ${measure}
									</label>
								</div><br>
							</g:each>
						</div>
					</div>
				<g:set var="cont" value="${cont + 1}"/>
				<g:if test="${cont == 5}">
					</div>
					<g:set var="cont" value="${1}"/>
				</g:if>
				</g:each>
		</g:form>
	</div>
</body>
</html>
