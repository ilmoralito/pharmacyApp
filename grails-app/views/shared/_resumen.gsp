<label>Monto ventas de contado</label>
<p>${balanceCashSales}</p>

<label>Monto ventas de credito</label>
<p>${balanceCreditSales}</p>

<g:if test="${balanceCanceledSales}">
    <label>Monto de ventas canceladas</label>
    <p>${balanceCanceledSales}</p>
</g:if>

<label>Monto de gastos</label>
<p>${expenseBalance}</p>

<label>Monto en caja</label>
<p><b>${balanceCashSales - expenseBalance}</b></p>


<label>Cantidad de ventas de contado</label>
<p>${quantityCashSales}</p>

<label>Cantidad de ventas de credito</label>
<p>${quantityCreditSales}</p>

<label>Cantidad de ventas Canceladas</label>
<p>${quantityCanceledSales}</p>

<label>Monto en abonos</label>
<p></p>
