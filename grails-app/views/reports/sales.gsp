<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Reporte de ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${results}">
				<table class="table">
					<colgroup>
			       <col span="1" style="width: 25%;">
			       <col span="1" style="width: 25%;">
			       <col span="1" style="width: 25%;">
			       <col span="1" style="width: 25%;">
			    </colgroup>
					<thead>
						<th>Fecha</th>
						<th>Monto de venta</th>
						<th>Monto de diario</th>
						<th>Monto en caja</th>
					</thead>
					<tbody>
						<g:each in="${results}" var="date">
							<tr>
								<td>${date.key}</td>
								<td>${date.value.salesAmount}</td>
								<td>${date.value.expensesDailyAmount}</td>
								<td>${(date.value.salesAmount ?: 0) - (date.value.expensesDailyAmount ?: 0)}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<p>Nada que mostrar</p>
			</g:else>
		</div>
		<div class="col-md-2">
			<h4>Filtrar</h4>
			<g:form action="sales">
				<div class="form-group">
					<label for="from" class="sr-only">Desde</label>
					<g:textField name="from" value="${params?.from}" class="form-control" placeholder="Desde..."/>
				</div>

				<div class="form-group">
					<label for="to" class="sr-only">Hasta</label>
					<g:textField name="to" value="${params?.to}" class="form-control" placeholder="Hasta..."/>
				</div>

				<g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
