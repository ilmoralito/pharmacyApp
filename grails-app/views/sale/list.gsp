<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, saleFilter, chosenLib, jquery-ui"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<g:link action="createGeneralSale" class="btn btn-primary">General</g:link>
				<g:link action="createSaleToClient" class="btn btn-primary">A cliente</g:link>
			</div>
		</div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-9">
			<g:if test="${sales}">
				<h4>Ventas</h4>
				<table class="table table-striped table-hover">
					<thead>
						<th width="1">#</th>
						<th>Fecha de venta</th>
						<th>Cliente</th>
						<th>Total de compra</th>
						<th>Tipo de compra</th>
						<th>Vendedor</th>
					</thead>
					<tbody>
						<g:each in="${sales}" var="sale" status="index">
							<tr>
								<td>
									<g:link action="show" id="${sale.id}">
										${index + 1}
									</g:link>
								</td>
								<td><g:formatDate date="${sale.dateCreated}" formatName="custom.date.format"/></td>
								<td>
									<g:if test="${sale.instanceOf(ni.sb.SaleToClient)}">
										${sale.client}
									</g:if>
								</td>
								<td>${sale.balance}</td>
								<td>
									<g:if test="${sale.instanceOf(ni.sb.SaleToClient)}">
										${sale.typeOfPurchase}
									</g:if>
								</td>
								<td>${sale.user.fullName}</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h4>Sin ventas...</h4>
			</g:else>
		</div>

		<div class="col-md-3">
			<h4>Filtar</h4>
			<g:form action="list">
				
				<h5>Fechas</h5>
				<div class="form-group">
					<label for="from" class="sr-only">Desde</label>
					<g:textField name="from" class="form-control" placeholder="Desde"/>
				</div>
				<div class="form-group">
					<label for="to" class="sr-only">Hasta</label>
					<g:textField name="to" class="form-control" placeholder="Hasta"/>
				</div>
				
				<h5>Clientes</h5>
				<g:select name="clients" from="${ni.sb.Client.findAllByStatus(true)}" class="form-control chosen-select" multiple tabindex="4" />

				<h5>Tipo de pago</h5>
				<div class="checkbox">
					<label>
						<g:checkBox name="typeOfPurchase" value="Contado" checked="false"/>
						Contado
					</label>
				</div>
				<div class="checkbox">
					<label>
						<g:checkBox name="typeOfPurchase" value="Credito" checked="false"/>
						Credito
					</label>
				</div>

				<h5>Anulado</h5>
				<div class="checkbox">
					<label>
						<g:checkBox name="canceled" value="true" checked="false"/>
						Esta anulado
					</label>
				</div>

				<h5>Vendedores</h5>
				<g:each in="${users}" var="user">
					<div class="checkbox">
						<label>
							<g:checkBox name="users" value="${user.id}" checked="false"/>
							${user.fullName}
						</label>
					</div>
				</g:each>

				<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
