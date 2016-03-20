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

<a href="#" id="triggerAddTelephone" class="btn btn-link btn-xs">
    Agregar telefono
</a>

<g:if test="${!client?.telephones}">
    <div class="form-group">
        <g:textField
            name="telephones"
            value="${client?.telephones}"
            class="form-control telephones"
            placeholder="Telefono"
            />
    </div>
</g:if>
<g:else>
    <g:each in="${client.telephones}" var="telephone">
        <div class="form-group">
            <g:textField
                name="telephones"
                value="${telephone}"
                class="form-control telephones"
                placeholder="Telefono"
                />
        </div>
    </g:each>
</g:else>

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