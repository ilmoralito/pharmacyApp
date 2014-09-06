<table class="table">
	<tbody class="col-md-4">
		<g:each in="${provider?.products ?: products}" var="${product}">
			<tr>
				<td>${product}</td>
				<td width="1">
					<g:if test="${actionName == 'create'}">
						<g:link event="removeProduct" params="[product:product]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</g:if>
					<g:else>
						<g:link action="removeProduct" params="[providerId:provider.id, product:product]">
							<span class="glyphicon glyphicon-trash"></span>
						</g:link>
					</g:else>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>