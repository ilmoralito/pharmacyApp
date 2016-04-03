<div class="form-group">
    <label>Numero de factura</label>
    <g:textField
        name="invoiceNumber"
        value="${purchaseOrder?.invoiceNumber}"
        class="form-control"
        placeholder="Numero de factura"
        autofocus="true"/>
</div>

<label>Tipo de pago</label>
<div class="radio">
    <label class="radio-inline">
        <g:radio name="paymentType" value="credit"/>
        Credito
    </label>
</div>

<div class="radio">
    <label class="radio-inline">
        <g:radio name="paymentType" value="cash"/>
        Contado
    </label>
</div>

<div id="target" class="form-group hide">
    <label>Fecha de pago</label>
    <g:textField
        name="deadline"
        value="${purchaseOrder?.deadline}"
        class="form-control"
        placeholder="Fecha de pago"/>
</div>

<label>Distribuidor</label>
<pharmacyApp:dealers type="radio"/>
