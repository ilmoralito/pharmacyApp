<p>Empresa</p>
<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField
        name="name"
        value="${company?.name}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="city">Ciudad</label>
    <g:select
        name="city"
        from="['Leon', 'Chinandega']"
        value="${company?.city}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="address">Direccion</label>
    <g:textField
        name="address"
        value="${company?.address}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:textField
        name="telephoneNumber"
        value="${company?.telephoneNumber}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="creditLimit">Limite de credito</label>
    <g:textField
        name="creditLimit"
        value="${company?.creditLimit}"
        class="form-control"/>
</div>

<p>Contacto</p>
<div class="form-group">
    <label for="contactFullName">Nombre de contacto</label>
    <g:textField
        name="contactFullName"
        value="${company?.contactFullName}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="contactTelephoneNumber">Telefono de contacto</label>
    <g:textField
        name="contactTelephoneNumber"
        value="${company?.contactTelephoneNumber}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="contactEmail">Emial de contacto</label>
    <g:textField
        name="contactEmail"
        value="${company?.contactEmail}"
        class="form-control"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${company?.enabled}"/>
        </label>
        Habilitado
    </div>
</g:if>
