<div class="form-group">
    <g:textField
        name="fullName"
        value="${client?.fullName}"
        class="form-control ${inputSize}"
        placeholder="Nombre del cliente"/>
</div>

<div class="form-group">
    <g:textField
        name="email"
        value="${client?.email}"
        class="form-control ${inputSize}"
        placeholder="Correo electronico"/>
</div>

<div class="form-group">
    <g:textField
        name="address"
        value="${client?.address}"
        class="form-control ${inputSize}"
        placeholder="Direccion"/>
</div>

<div class="form-group">
    <g:textField
        name="telephoneNumber"
        value="${client?.telephoneNumber}"
        class="form-control ${inputSize}"
        placeholder="Numero de telefono"/>
</div>
