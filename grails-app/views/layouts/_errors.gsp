<g:hasErrors bean="${instance}">
	<br>
	<div class="alert alert-danger">
		<a class="close" data-dismiss="alert" href="#">&times;</a>
		<ul class="errors" role="alert">
			<g:eachError bean="${instance}" var="error">
				<li><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</div>
</g:hasErrors>
