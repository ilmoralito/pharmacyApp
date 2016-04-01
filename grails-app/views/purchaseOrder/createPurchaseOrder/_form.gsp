<label>Numero de factura</label>
<div class="form-group">
    <g:textField
        name="invoiceNumber"
        value="${purchaseOrder?.invoiceNumber}"
        class="form-control"
        placeholder="Numero de factura"/>
</div>

<label>Distribuidor</label>
<g:each in="${dealers}" var="distributor">
    <div class="radio">
        <label>
            <g:radio name="distributor" value="${distributor.id}"/>
            ${distributor.name}
        </label>
    </div>
    <ol>
        <g:each in="${distributor.providers}" var="provider">
            <li>${provider.name}</li>
        </g:each>
    </ol>
</g:each>

<label>Tipo de pago</label>
<div class="radio">
    <label>
        <g:radio name="paymentType" value="credit"/>
        Credito
    </label>
</div>

<div class="radio">
    <label>
        <g:radio name="paymentType" value="cash"/>
        Contado
    </label>
</div>
