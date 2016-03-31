

<div class="form-group">
    <g:textField
        name="fullName"
        value="${user?.fullName}"
        class="form-control"
        placeholder="Nombre completo"
        autofocus="true"/>
</div>

<div class="form-group">
    <input
        type="email"
        id="email"
        name="email"
        value="${user?.email}"
        class="form-control"
        placeholder="Email">
</div>

<div class="form-group">
    <g:textField
        name="telephoneNumber"
        value="${user?.telephoneNumber}"
        class="form-control"
        placeholder="Telefono"/>
</div>

<div class="form-group">
    <g:select
        name="authority"
        from="['USER', 'ADMIN']"
        keys="['ROLE_USER', 'ROLE_ADMIN']"
        value="${user?.getAuthorities()?.getAt(0)?.authority}"
        class="form-control"/>
</div>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${user?.enabled}"/>
        </label>
        Habilitado
    </div>
</g:if>
