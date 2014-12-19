<table class="table table-hover">
	<tbody>
		<g:each in="${results}" var="result">
			<tr>
				<td colspan="5" style="border:0;"><strong>${result.key}</strong></td>
			</tr>
			<g:each in="${result.value}" var="item">
				<tr>
					<td colspan="5">${item.key}</td>
				</tr>
				<g:each in="${item.value}" var="b">
					<g:form>
						<g:hiddenField name="id" value="${b.id}"/>

						<tr>
							<td style="vertical-align:middle;">${b.sellingPrice}</td>
							<td style="vertical-align:middle;">${b.product.location}</td>
							<td>
								<g:set var="q" value="${items.find { it.item == b }?.quantity ?: 0}"/>
								<g:set var="quantity" value="${items ? b.quantity - q : b.quantity}"/>

								<input type="number" name="quantity" min="1" max="${quantity}" required class="form-control input-sm" placeholder="${quantity}"/>
							</td>
							<td style="vertical-align:middle;">
								<g:submitButton name="addItem" value="Ir" class="btn btn-primary btn-xs"/>
							</td>
						</tr>
					</g:form>
				</g:each>
			</g:each>
		</g:each>
	</tbody>
</table>