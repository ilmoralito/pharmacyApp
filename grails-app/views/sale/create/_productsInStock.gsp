<table class="table table-hover">
	<tbody>
		<g:each in="${productsInStock.keySet()}" var="presentation" status="i">
			<tr>
				<td colspan="6">${presentation}</td>
			</tr>
			<tr>
				<td><span class="glyphicon glyphicon-stats"></span></td>
				<td style="text-align:center;"><span class="glyphicon glyphicon-calendar"></span></td>
				<td style="text-align:center;"><span class="glyphicon glyphicon-chevron-down"></span></td>
				<td></td>
				<td></td>
			</tr>
			<g:each in="${productsInStock[presentation]}" var="item">
				<g:form>
					<g:hiddenField name="id" value="${item.id}"/>
					<g:hiddenField name="presentationId" value="${item.presentation.id}"/>
					<g:hiddenField name="measure" value="${item.measure}"/>
					<g:hiddenField name="bash" value="${item.bash}"/>
					<tr>
						<td>${item.measure}</td>
						<td>${item.bash.format("MM-yy")}</td>
						<td>${item.sellingPrice}</td>
						<td>
							<input type="number" name="quantity" id="number" class="form-control input-sm" max="${item.quantity}" min="1" placeholder="${item.quantity}">
						</td>
						<td>
							<g:submitButton name="confirm" value="Ir" class="btn btn-primary btn-xs"/>
						</td>
					</tr>
				</g:form>
			</g:each>
		</g:each>
	</tbody>
</table>
