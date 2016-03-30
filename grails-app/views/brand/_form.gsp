<div class="form-group">
    <g:textField
        name="name"
        value="${brand?.name}"
        class="form-control"
        placeholder="Marca"
        autofocus="true"/>
</div>

<pharmacyApp:details brand="${brand}"/>
