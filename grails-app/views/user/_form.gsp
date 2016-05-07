<div class="form-group">
    <label for="fullName">Nombre completo</label>
    <g:textField
        name="fullName"
        value="${user?.fullName}"
        class="form-control"
        autofocus="true"/>
</div>

<div class="form-group">
    <label for="email">Email</label>
    <input
        type="email"
        id="email"
        name="email"
        value="${user?.email}"
        class="form-control">
</div>

<div class="form-group">
    <label for="telephoneNumber">Numero de telefono</label>
    <g:textField
        name="telephoneNumber"
        value="${user?.telephoneNumber}"
        class="form-control"/>
</div>

<g:if test="${actionName == 'show' || actionName == 'list'}">
    <div class="form-group">
        <label for="authority">Autoridad</label>
        <g:select
            name="authority"
            from="['USER', 'ADMIN']"
            keys="['ROLE_USER', 'ROLE_ADMIN']"
            value="${user?.getAuthorities()?.getAt(0)?.authority}"
            class="form-control"/>
    </div>
</g:if>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${user?.enabled}"/>
            Habilitado
        </label>
    </div>
</g:if>
