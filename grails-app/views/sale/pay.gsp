<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Abonos</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, pay"/>
</head>
<body>
<style>
	hr{
		margin-bottom: 5px;
		margin-top: 25px;
	};
</style>
	<div class="row">
		<div class="col-md-7">
			Poner lista de abonos
		</div>
		<div class="col-md-4">
			<g:form controller= "sales" action="pay">
				<g:hiddenField name="balance" value="${saleInstance.balance}"/>
				<g:hiddenField name="user" value="${saleInstance.id}"/>
				<g:hiddenField name="sale" value=""/>
				<g:hiddenField name="receiptNumber" value="valor"/>
				<g:hiddenField name="change" value="change"/>
				<div class="radio">
					<label>
					    <input type="radio" name="pay" id="abonar" value="Abonar" checked>
						Abonar
					</label>
				</div>
				<div class="radio">
					<label>
					    <input type="radio" name="pay" id="saldar" value="saldar">
						Cancelar cuenta
					</label>
				</div>
				<div class="form-group">
					<label for="payment">Cantidad a abonar</label>
					<g:textField type="payment" class="form-control" name="payment"/>
				</div>
				<div class="form-group">
					<label for="payment">Cantidad con la que paga</label>
					<g:textField type="amountPayment" class="form-control" name="amountPayment"/>
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
					   	<div class="col-md-6"></div>
					   	<hr>
					   	<div id="info"></div>
				  </div>
				  </div>
				</div>
				<div>
					<g:submitButton name="btnregistration" value="etiqueta" class="btn btn-default pull-right"/>
				</div>
			</g:form>

		</div>
	</div>
</body>
</html>
