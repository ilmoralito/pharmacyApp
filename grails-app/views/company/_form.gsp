<p>Empresa</p>
<div class="form-group">
    <g:textField
        name="name"
        value="${company?.name}"
        class="form-control"
        placeholder="Nombre"/>
</div>

<!--TODO: Get cities list from constraints in company domain class-->
<div class="form-group">
    <g:select
        name="city"
        from="['Leon', 'Chinandega']"
        value="${company?.city}"
        class="form-control"/>
</div>

<div class="form-group">
    <g:textField
        name="address"
        value="${company?.address}"
        class="form-control"
        placeholder="Direcccion"/>
</div>

<div class="form-group">
    <g:textField
        name="telephoneNumber"
        value="${company?.telephoneNumber}"
        class="form-control"
        placeholder="Telefono"/>
</div>

<p>Contacto</p>
<div class="form-group">
    <g:textField
        name="contactFullName"
        value="${company?.contactFullName}"
        class="form-control"
        placeholder="Nombre de contacto"/>
</div>

<div class="form-group">
    <g:textField
        name="contactTelephoneNumber"
        value="${company?.contactTelephoneNumber}"
        class="form-control"
        placeholder="Telefono de contacto"/>
</div>

<div class="form-group">
    <g:textField
        name="contactEmail"
        value="${company?.contactEmail}"
        class="form-control"
        placeholder="Email de contacto"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${company?.enabled}"/>
        </label>
        Habilitado
    </div>
</g:if>
