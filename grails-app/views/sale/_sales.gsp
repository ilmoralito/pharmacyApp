<g:if test="${sales}">
	<h4>Detalle de venta</h4>
	<table class="table table-striped">
		<thead>
			<th width="1"></th>
		</thead>
		<tbody></tbody>
	</table>
	<g:each in="${sales}" var="sale" status="index">
		<tr>
			<td>${index + 1}</td>
		</tr>
	</g:each>
</g:if>
<g:else>
	<h4>...</h4>
</g:else>