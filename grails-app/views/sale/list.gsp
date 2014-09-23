<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Ventas</title>
	<r:require modules="bootstrap-css, bootstrap-collapse, saleFilter, jquery-ui"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<g:link action="create" class="btn btn-primary">Vender</g:link>
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
					</thead>
					<tbody>
						<g:each in="${sales}" var="sale" status="index">
							<tr>
								<td>${index + 1}</td>
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
				
				<h5 id="clientLabel">Clientes</h5>
				<div id="showHideClients">
					<g:each in="${ni.sb.Client.findAllByStatus(true)}" var="client">
						<div class="checkbox">
							<label>
								<g:checkBox name="clients" value="${client}" checked="false"/>
								${client}
							</label>
						</div>
					</g:each>
				</div>

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

				<g:submitButton name="filter" value="Filtrar" class="btn btn-primary btn-block"/>
			</g:form>
		</div>
	</div>
</body>
</html>
