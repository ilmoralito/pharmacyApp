<br>
<table class="table">
	<tbody>
		<g:each in="${provider.products}" var="${product}">
			<tr>
				<td>${product}</td>
				<td width="1">
					<g:link action="removeProduct" params="[providerId:provider.id, product:product]">
						<span class="glyphicon glyphicon-trash"></span>
					</g:link>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>