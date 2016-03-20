<div class="form-group">
    <g:textField
        name="fullName"
        value="${client?.fullName}"
        class="form-control"
        placeholder="Nombre completo"/>
</div>

<div class="form-group">
    <g:textField
        name="address"
        value="${client?.address}"
        class="form-control"
        placeholder="Direccion"/>
</div>

<div class="form-group">
    <g:textField
        name="identificationCard"
        value="${client?.identificationCard}"
        class="form-control"
        placeholder="Cedula"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="form-group">
        <g:select
            name="status"
            from="['Activo', 'Inactivo']"
            keys="[true, false]"
            value="${client?.status}"
            class="form-control"/>
    </div>
</g:if>

<g:if test="${!client?.telephones}">
    <a href="#" id="triggerAddTelephone" class="btn btn-link btn-xs">
        Agregar telefono
    </a>

    <div class="form-group">
        <input
            type="telephone"
            id="telephones"
            name="telephones"
            class="form-control telephones"
            maxlength="8"
            placeholder="Telefono">
    </div>
</g:if>
