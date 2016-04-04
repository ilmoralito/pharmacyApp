<div class="form-group">
    <g:textField
        name="invoiceNumber"
        value="${invoiceNumber}"
        class="form-control"
        placeholder="Numero de factura"
        autofocus="true"/>
</div>

<pharmacyApp:paymentTypeBox type="radio"/>

<div id="target" class="form-group hide">
    <g:textField
        name="paymentDate"
        value="${paymentDate}"
        class="form-control"
        placeholder="Fecha de pago"/>
</div>

<pharmacyApp:dealers type="radio" dealerList="[distributor?.id]"/>
