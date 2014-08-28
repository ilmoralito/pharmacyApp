<g:if test="${controllerName == 'user'}">
	<g:hasErrors bean="${userInstance}">
		<br>
		<div class="alert alert-danger">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<ul class="errors" role="alert">
				<g:eachError bean="${userInstance}" var="error">
					<li><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</div>
	</g:hasErrors>
</g:if>