<div class="form-group">
    <g:textField
        name="name"
        value="${provider?.name}"
        class="form-control"
        placeholder="Nombre"/>
</div>

<div class="form-group">
    <g:textField
        name="address"
        value="${provider?.address}"
        class="form-control"
        placeholder="Direccion"/>
</div>

<div class="form-group">
    <g:textField
        name="telephoneNumber"
        value="${provider?.telephoneNumber}"
        class="form-control"
        placeholder="Numero telefonico"
        maxLength="8"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${provider?.enabled}"/>
        </label>
        Habilitado
    </div>
</g:if>
