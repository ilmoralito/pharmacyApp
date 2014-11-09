<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Gastos diarios</title>
	<r:require modules="bootstrap-css, bootstrap-collapse"/>
</head>
<body>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${daily}">
				<h4>Gastos de <g:formatDate date="${daily.date}" formatName="custom.date.format"/></h4>
				<table class="table table-hover">
					<thead>
						<th width="1">Hora</th>
						<th>Descripcion</th>
						<th>Cantidad</th>
					</thead>
					<tbody>
						<g:each in="${daily.expenses}" var="expense">
							<tr>
								<td><g:formatDate date="${expense.dateCreated}" formatName="hour.date.format"/></td>
								<td>${expense.description}</td>
								<td>${expense.quantity}</td>
							</tr>
						</g:each>
						<tr>
							<td colspan="2">TOTAL</td>
							<td>${daily.expenses.quantity.sum()}</td>
						</tr>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h4>...</h4>
			</g:else>
		</div>
		<div class="col-md-2">
			<g:form action="list">
				<div class="form-group">
					<label for="date">Diarios</label>
					<g:select name="date" from="${dates}" value="${daily?.date?.format('yyyy-MM-dd')}" class="form-control"/>
				</div>
				<g:submitButton name="send" value="Buscar" class="btn btn-primary btn-block"/>
			</g:form>
			<br>

			<g:form action="addExpense">
				<div class="form-group">
					<label for="description" class="sr-only">Descripcion</label>
					<g:textArea name="description" placeholder="Razon" class="form-control"/>
				</div>

				<div class="form-group">
					<label for="quantity" class="sr-only">Cantidad</label>
					<g:textField name="quantity" placeholder="Cantidad" class="form-control"/>
				</div>

				<g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
