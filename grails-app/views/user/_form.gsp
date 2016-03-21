<div class="form-group">
    <input
        type="email"
        id="email"
        name="email"
        value="${user?.email}"
        class="form-control"
        placeholder="Email"
        autofocus="true">
</div>

<div class="form-group">
    <g:textField
        name="fullName"
        class="form-control"
        value="${user?.fullName}"
        placeholder="Nombre completo"/>
</div>

<div class="form-group">
    <g:select
        name="authority"
        from="['USER', 'ADMIN']"
        keys="['ROLE_USER', 'ROLE_ADMIN']"
        value="${user?.getAuthorities()?.getAt(0)?.authority}"
        class="form-control"/>
</div>