<div class="form-group">
    <g:textField
        name="name"
        value="${product?.name}"
        class="form-control"
        placeholder="Nombre del producto"
        autofocus="true"/>
</div>

<g:if test="${actionName == 'createMedicine' || product instanceof ni.sb.Medicine}">
    <div class="form-group">
        <g:textField
            name="code"
            value="${product?.code}"
            class="form-control"
            placeholder="Codigo"/>
    </div>
    <div class="form-group">
        <g:textField
            name="genericName"
            value="${product?.genericName}"
            class="form-control"
            placeholder="Nombre Generico"/>
    </div>
</g:if>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${product.enabled}"/>
            Activo
        </label>
    </div>
</g:if>