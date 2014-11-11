<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Abonos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, pay"/>
</head>
<body>
<style>hr{ margin-bottom: 5px; margin-top: 25px;};</style>
	<div class="row">
		<div class="col-md-7">
			<g:if test="${payInstance}">
				<h4>Abonos efectuados</h4>
				<table class="table">
					<thead>
						<th>Recibo</th>
						<th>Pago</th>
						<th>Vuelto</th>
						<th>Saldo</th>
						<th width="1"></th>
					</thead>
					<tbody>
						<g:each in="${payInstance}" var="pay" status="index">
							<tr>
								<td>${pay.receiptNumber}</td>
								<td>${pay.payment}</td>
								<td>${pay.change}</td>
								<td>${saleInstance.balance - pay.payment}</td>
								<td width="1">
									Eliminar.
								</td>
							</tr>
						</g:each>
						<tr>
							<td colspan="4">TOTAL</td>
							<td colspan="2">mostrar total</td>
						</tr>
					</tbody>
				</table>
			</g:if>
		</div>
		<div class="col-md-4">
			<g:form controller= "sales" action="pay">
				<g:hiddenField name="balance" value="${saleInstance.balance}"/>
				<g:hiddenField name="user" value=""/>
				<g:hiddenField name="sale" value="${saleInstance.id}"/>
				<g:hiddenField name="receiptNumber" value="valor"/>
				<g:hiddenField name="change" value="change"/>
				<div class="form-group">
					<label for="payment">Cantidad a abonar</label>
					<g:textField type="payment" class="form-control" name="payment" required=""/>
				</div>
				<div class="form-group">
					<label for="payment">Cantidad con la que paga</label>
					<g:textField type="amountPayment" class="form-control" name="amountPayment" required=""/>
				</div>
				<div class="panel panel-default">
				  <div class="panel-body">
				  <div class="row">
				  		<div class="col-md-6">Cuenta</div>
					   	<div class="col-md-6">${saleInstance.balance}</div>
					   	<hr>
					   	<div class="col-md-6">Saldo</div>
					   	<div class="col-md-6" id="currentBalance"></div>
					   	<hr>
				  		<div class="col-md-6">Cambio</div>
					   	<div class="col-md-6" id="changeLabel"></div>
					   	<hr>
					   	<div class="col-md-6">Recibo</div>
					   	<g:set var="receiptNumber" value="${pharmacyApp.calcReceiptNumber()}"/>
					   	<div class="col-md-6">${receiptNumber}</div>
					   	<hr>
					   	<div class="col-md-12"><strong><p id="info"></p></strong></div>
				  </div>
				  </div>
				</div>
				<div>
					<g:submitButton name="btnregistration" value="Registrar" class="btn btn-primary pull-right"/>
				</div>
			</g:form>

		</div>
	</div>
</body>
</html>
