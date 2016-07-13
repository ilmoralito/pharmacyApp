<div class="row">
    <div class="col-md-6">
        <p>Informacion de empresa</p>
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Nombre</label>
                    <g:textField
                        name="name"
                        value="${company?.name}"
                        class="form-control"/>
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="city">Ciudad</label>
                    <g:select
                        noSelection="[null: 'Selecciona una ciudad']"
                        name="city"
                        from="['Leon', 'Chinandega']"
                        value="${company?.city}"
                        class="form-control"/>
                </div>
            </div>
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

        <g:if test="${actionName == 'show'}">
            <div class="checkbox">
                <label>
                    <g:checkBox name="enabled" value="${company?.enabled}"/>
                </label>
                Habilitado
            </div>
        </g:if>
    </div>

    <div class="col-md-6">
        <p>Contacto de la empresa</p>
        <div class="form-group">
            <label for="companyContact.fullName">Nombre</label>
            <g:textField
                name="companyContact.fullName"
                value="${company?.companyContact?.fullName}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="companyContact.telephoneNumber">Telefono</label>
            <g:textField
                name="companyContact.telephoneNumber"
                value="${company?.companyContact?.telephoneNumber}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="companyContact.personalTelephoneNumber">Telefono personal</label>
            <g:textField
                name="companyContact.personalTelephoneNumber"
                value="${company?.companyContact?.personalTelephoneNumber}"
                class="form-control"/>
        </div>

        <div class="form-group">
            <label for="companyContact.email">Correo</label>
            <g:field type="email"
                name="companyContact.email"
                value="${company?.companyContact?.email}"
                class="form-control"/>
        </div>
    </div>
</div>
