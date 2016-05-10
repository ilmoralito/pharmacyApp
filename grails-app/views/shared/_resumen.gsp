<p>Resumen</p>

<label>Monto</label>
<p>${balance}</p>

<label>Gastos</label>
<p>${expenseBalance}</p>

<label>Monto en caja</label>
<p>${balance - expenseBalance}</p>

<g:if test="${balanceCanceledSales}">
    <label>Monto de ventas canceladas</label>
    <p>${balanceCanceledSales}</p>
</g:if>