<g:applyLayout name="threeColumns">
    <head>
        <title>Inventario de productos de marca</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
    <g:render template="nav"/>

        <g:if test="${brandProducts}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Marca</th>
                    <th>Detalle</th>
                    <th>Cantidad</th>
                </thead>
                <tbody>
                    <g:each in="${brandProducts}" var="${brandProduct}">
                        <tr>
                            <td colspan="4">${brandProduct.labName}</td>
                        </tr>
                        <g:each in="${brandProduct.labProducts}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.brand}</td>
                                <td>${product.detail}</td>
                                <td>${product.quantity}</td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin artuclos que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <g:link action="print" class="btn btn-default btn-block">Imprimir</g:link>
    </content>
</g:applyLayout>
