<div class="form-group">
    <label for="fullName">Nombre completo</label>
    <g:textField
        name="fullName"
        value="${employee?.fullName}"
        class="form-control"
        autofocus="true"/>
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero telefonico</label>
    <g:textField
        name="telephoneNumber"
        value="${employee?.telephoneNumber}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="identificationCard">Cedula</label>
    <g:textField
        name="identificationCard"
        value="${employee?.identificationCard}"
        class="form-control"/>
</div>

<div class="form-group">
    <label for="inss">Numero de INSS</label>
    <g:textField
        name="inss"
        value="${employee?.inss}"
        class="form-control"/>
</div>
