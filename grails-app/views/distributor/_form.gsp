<p>Distribuidor</p>
<div class="form-group">
    <g:textField
        name="name"
        value="${dealer?.name}"
        class="form-control"
        placeholder="Nombre del distribudor"/>
</div>

<div class="form-group">
    <g:textField
        name="telephoneNumber"
        value="${dealer?.telephoneNumber}"
        class="form-control"
        placeholder="Numero telefonico"/>
</div>

<div class="form-group">
    <input
        type="number"
        id="daysToPay"
        name="daysToPay"
        min="1",
        value="${dealer?.daysToPay}"
        class="form-control"
        placeholder="Limite de pago">
</div>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${dealer?.enabled}"/>
            Habilitado
        </label>
    </div>
</g:if>

<p>Contacto</p>
<div class="form-group">
    <g:textField
        name="contact.fullName"
        value="${dealer?.contact?.fullName}"
        class="form-control"
        placeholder="Nombre"/>
</div>

<div class="form-group">
    <g:textField
        name="contact.email"
        value="${dealer?.contact?.email}"
        class="form-control"
        placeholder="Email"/>
</div>

<div class="form-group">
    <g:textField
        name="contact.telephoneNumber"
        value="${dealer?.contact?.telephoneNumber}"
        class="form-control"
        placeholder="Numero telefonico"/>
</div>

<p>Laboratorios</p>
<pharmacyApp:providers providerList="${dealer?.providers ?: params.list('providers')}"/>
