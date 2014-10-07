<div class="col-md-12">
	<ul class="nav nav-tabs">
		<li class="${(actionName == 'quantity') ? 'active' : ''}">
			<g:link controller="notifications" action="quantity">Existencias bajas</g:link>
		</li>

		<li class="${(actionName == 'expire') ? 'active' : ''}">
			<g:link controller="notifications" action="expire">Por vencerse</g:link>
		</li>

		<li class="${(actionName == 'expired') ? 'active' : ''}">
			<g:link controller="notifications" action="expired">Vencidos</g:link>
		</li>

		<li class="${(actionName == 'pendingOrders') ? 'active' : ''}">
			<g:link controller="notifications" action="pendingOrders">Pedidos pendientes</g:link>
		</li>

		<li class="${(actionName == 'clientPayments') ? 'active' : ''}">
			<g:link controller="notifications" action="clientPayments">Pagos de clientes pendientes</g:link>
		</li>
	</ul>
</div>