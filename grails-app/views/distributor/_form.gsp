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

<g:if test="${actionName == 'show'}">
    <p>Habilitado</p>
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${dealer?.enabled}"/>
        </label>
    </div>
</g:if>
