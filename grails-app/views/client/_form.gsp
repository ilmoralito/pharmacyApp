<div class="form-group">
    <label for="fullName">Nombre completo</label>
    <g:textField
        name="fullName"
        value="${client?.fullName}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="email">Email</label>
    <g:field type="email" name="email" value="${client?.email}" class="form-control"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:textField
        name="telephoneNumber"
        value="${client?.telephoneNumber}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="address">Direccion</label>
    <g:textField
        name="address"
        value="${client?.address}"
        class="form-control"/>
</div>
