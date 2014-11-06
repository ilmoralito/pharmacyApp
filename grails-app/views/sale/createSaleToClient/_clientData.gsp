<g:if test="${client || typeOfPurchase}">
	<table class="table" style="margin-bottom:10px;">
		<tbody>
			<tr>
				<td style="border:0;">Cliente</td>
				<td style="border:0;">${client}</td>
			</tr>
			<tr>
				<td>Tipo de compra</td>
				<td>${typeOfPurchase}</td>
			</tr>
		</tbody>
	</table>
	<g:link event="selectCustomer" class="btn btn-primary btn-block">Cambiar datos venta</g:link>
	<br>
</g:if>