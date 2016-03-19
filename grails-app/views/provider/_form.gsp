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
        placeholder="Numero telefonico"/>
</div>
<g:if test="${actionName == 'show'}">
    <div class="form-group">
        <g:select
            name="status"
            from="${['Activo', 'Inactivo']}"
            keys="[true, false]"
            value="${provider?.status}"
            class="form-control"/>
    </div>
</g:if>