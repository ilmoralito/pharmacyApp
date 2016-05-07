<div class="form-group">
    <label for="name">Nombre</label>
    <g:textField
        name="name"
        value="${product?.name}"
        class="form-control"
        autofocus="true"/>
</div>

<g:if test="${product instanceof ni.sb.Medicine || actionName == 'medicineList'}">
    <div class="form-group">
        <label for="code">Codigo</label>
        <g:textField
            name="code"
            value="${product?.code}"
            class="form-control"/>
    </div>

    <div class="form-group">
        <label for="genericName">Nombre Generico</label>
        <g:textField
            name="genericName"
            value="${product?.genericName}"
            class="form-control"
            list="genericNames"/>
    </div>

    <pharmacyApp:presentations product="${product}"/>
</g:if>

<g:if test="${product instanceof ni.sb.BrandProduct || actionName == 'brandProductList'}">
    <pharmacyApp:brands product="${product}"/>
</g:if>

<g:if test="${actionName == 'show'}">
    <p>Estado</p>
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${product.enabled}"/>
        </label>
        Activo
    </div>
</g:if>