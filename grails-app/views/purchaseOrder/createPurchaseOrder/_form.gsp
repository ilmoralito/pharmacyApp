<div class="form-group">
    <label for="invoiceNumber">Numero de factura</label>
    <g:textField
        name="invoiceNumber"
        value="${invoiceNumber}"
        class="form-control"
        autofocus="true"/>
</div>

<pharmacyApp:paymentTypeBox type="radio" paymentType="${paymentType}"/>

<pharmacyApp:dealers
    name="distributor.id"
    type="radio"
    dealerList="[distributor?.id]"
    showMessage="true"/>
