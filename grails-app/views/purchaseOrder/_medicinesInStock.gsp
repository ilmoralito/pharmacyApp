<table class="table table-hover">
	<colgroup>
		<col span="1" style="width: 16.6%;">
		<col span="1" style="width: 16.6%;">
		<col span="1" style="width: 16.6%;">
		<col span="1" style="width: 16.6%;">
		<col span="1" style="width: 16.6%;">
		<col span="1" style="width: 16.6%;">
	</colgroup>
	<thead>
		<th>Nombre</th>
		<th>Medida</th>
		<th>Ubicacion</th>
		<th>Cantidad</th>
		<th>Precion venta</th>
		<th>Total</th>
	</thead>
	<tbody>
		<g:each in="${list}" var="m">
			<tr>
				<td>${m}</td>
				<td>${m.measure}</td>
				<td>${m.quantity}</td>
				<td>${m.sellingPrice}</td>
				<td>${m.sellingPrice * m.quantity}</td>
			</tr>
		</g:each>
		<tr>
			<td colspan="5"></td>
			<td>
				${list.collect { it.sellingPrice * it.quantity }.sum()}
			</td>
		</tr>
	</tbody>
</table>