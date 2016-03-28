<div class="form-group">
    <g:textField
        name="name"
        value="${product?.name}"
        class="form-control"
        placeholder="Nombre del producto"
        autofocus="true"/>
</div>

<g:if test="${product instanceof ni.sb.Medicine || actionName == 'medicineList'}">
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
            placeholder="Nombre Generico"
            list="genericNames"/>

        <!--
        TODO
        <datalist id="genericNames">
            <g:each in="${1..10}" var="n">
                <option value="peluso"/>
            </g:each>
        </datalist>
        -->
    </div>

    <p>Presentaciones</p>
    <pharmacyApp:presentations product="${product}"/>
</g:if>

<g:if test="${actionName == 'show'}">
    <div class="checkbox">
        <label>
            <g:checkBox name="enabled" value="${product.enabled}"/>
        </label>
        Activo
    </div>
</g:if>