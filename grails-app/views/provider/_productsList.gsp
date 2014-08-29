<br>
<table class="table">
	<tbody>
		<g:each in="${provider.products}" var="${product}" status="i">
			<tr>
				<td>${product}</td>
				<td width="1">
					<g:link action="removeProduct" id="${i}">
						<span class="glyphicon glyphicon-trash"></span>
					</g:link>
				</td>
			</tr>
		</g:each>
	</tbody>
</table>