<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField
        name="name"
        value="${provider?.name}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="address">Direccion</label>
    <g:textField
        name="address"
        value="${provider?.address}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:textField
        name="telephoneNumber"
        value="${provider?.telephoneNumber}"
        class="form-control"
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
