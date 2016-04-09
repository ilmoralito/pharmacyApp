<div class="form-group">
    <g:textField
        name="invoiceNumber"
        value="${invoiceNumber}"
        class="form-control"
        placeholder="Numero de factura"
        autofocus="true"/>
</div>

<pharmacyApp:paymentTypeBox type="radio" paymentType="${paymentType}"/>

<pharmacyApp:dealers type="radio" dealerList="[distributor?.id]" showMessage="true"/>
