<div class="row">
    <div class="col-md-3">
        <p>Datos de distribuidor</p>
        <div class="form-group">
            <label for="name">Nombre</label>
            <g:textField
                name="name"
                value="${dealer?.name}"
                class="form-control"/>
        </div>
        
        <div class="form-group">
            <label for="telephoneNumber">Numero telefonico</label>
            <g:textField
                name="telephoneNumber"
                value="${dealer?.telephoneNumber}"
                class="form-control"/>
        </div>
        
        <div class="form-group">
            <label for="daysToPay">Limite dias de credito</label>
            <input
                type="number"
                id="daysToPay"
                name="daysToPay"
                min="1",
                value="${dealer?.daysToPay}"
                class="form-control">
        </div>
        
        <g:if test="${actionName == 'show'}">
            <div class="checkbox">
                <label>
                    <g:checkBox name="enabled" value="${dealer?.enabled}"/>
                    Habilitado
                </label>
            </div>
        </g:if>
    </div>

    <div class="col-md-3">
        <p>Datos de contacto</p>
        <div class="form-group">
            <label for="contact.fullName">Nombre de contacto</label>
            <g:textField
                name="contact.fullName"
                value="${dealer?.contact?.fullName}"
                class="form-control"/>
        </div>
        
        <div class="form-group">
            <label for="contact.email">Email de contacto</label>
            <g:textField
                name="contact.email"
                value="${dealer?.contact?.email}"
                class="form-control"/>
        </div>
        
        <div class="form-group">
            <label for="contact.telephoneNumber">Telefono de contacto</label>
            <g:textField
                name="contact.telephoneNumber"
                value="${dealer?.contact?.telephoneNumber}"
                class="form-control"/>
        </div>
    </div>

    <div class="col-md-4">
        <p>Laboratorios</p>
        <label for="providers">Seleciona laboratorios</label>
        <pharmacyApp:providers providerList="${dealer?.providers ?: params.list('providers')}"/>
    </div>
</div>
