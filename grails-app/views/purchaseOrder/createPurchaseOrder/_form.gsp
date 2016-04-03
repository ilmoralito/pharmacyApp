<div class="form-group">
    <label>Numero de factura</label>
    <g:textField
        name="invoiceNumber"
        value="${purchaseOrder?.invoiceNumber}"
        class="form-control"
        placeholder="Numero de factura"/>
</div>

<!-- TODO: Add calendar here-->
<div class="form-group">
    <label>Fecha de pago</label>
    <g:textField
        name="deadline"
        value="${purchaseOrder?.deadline}"
        class="form-control"
        placeholder="Fecha de pago"/>
</div>

<label>Distribuidor</label>
<pharmacyApp:dealers/>

<label>Tipo de pago</label>
<div class="radio">
    <label class="radio-inline">
        <g:radio name="paymentType" value="credit"/>
    </label>
    <span>Credito</span>
</div>

<div class="radio">
    <label class="radio-inline">
        <g:radio name="paymentType" value="cash"/>
    </label>
    <span>Contado</span>
</div>
