<div class="col-md-12">
	<ul class="nav nav-tabs">
		<li class="${(actionName == 'quantity') ? 'active' : ''}">
			<g:link controller="notifications" action="quantity">
				<g:if test="${session.q > 0}">
                    <span class="badge notifications">${session.q}</span>
                </g:if>
				Existencias bajas
			</g:link>
		</li>

		<li class="${(actionName == 'expire') ? 'active' : ''}">
			<g:link controller="notifications" action="expire">
				<g:if test="${session.ex > 0}">
                    <span class="badge notifications">${session.ex}</span>
                </g:if>
				Por vencerse
			</g:link>
		</li>

		<li class="${(actionName == 'expired') ? 'active' : ''}">
			<g:link controller="notifications" action="expired">
				<g:if test="${session.exd > 0}">
                    <span class="badge notifications">${session.exd}</span>
                </g:if>
				Vencidos
			</g:link>
		</li>

		<li class="${(actionName == 'pendingOrders') ? 'active' : ''}">
			<g:link controller="notifications" action="pendingOrders">
				<g:if test="${session.po > 0}">
                    <span class="badge notifications">${session.exd}</span>
                </g:if>
				Pedidos pendientes
			</g:link>
		</li>

		<li class="${(actionName == 'clientPayments') ? 'active' : ''}">
			<g:link controller="notifications" action="clientPayments">
				Pagos de clientes pendientes
			</g:link>
		</li>
	</ul>
</div>