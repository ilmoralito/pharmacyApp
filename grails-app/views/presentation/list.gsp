<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Presentaciones</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, app"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:if test="${presentations}">
		<table class="table">
			<thead>
				<th>Presentaciones</th>
				<th width="1"></th>
			</thead>
			<tbody>
				<g:each in="${presentations}" var="presentation">
					<tr id="${presentation}">
						<td>${presentation}</td>
						<td>
							<g:link action="delete" params="[id:presentation.id]">
								<span class="glyphicon glyphicon-trash"></span>
							</g:link>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<g:form action="addMeasures">
								<g:hiddenField name="id" value="${presentation.id}"/>
								<g:hiddenField name="presentation" value="${presentation}"/>
								<g:hiddenField name="productId" value="${params?.productId}"/>

								<g:each in="${grailsApplication.config.ni.sb.presentationsAndMeasures[presentation.name]}" var="measure">
									<div class="checkbox">
										<label>
											<g:checkBox name="measures" value="${measure}" checked="${presentation.measures.contains(measure)}"/>
											${measure}
										</label>
									</div>
								</g:each>
								<g:submitButton name="confirm" value="Agregar unidad de medida" class="btn btn-primary btn-xs"/>
							</g:form>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<h4>Sin presentaciones</h4>
	</g:else>
</body>
</html>
