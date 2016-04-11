<g:applyLayout name="threeColumns">
    <head>
        <title>Inventario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${items}">
            <table class="table table-hover">
                <caption>Productos</caption>
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>
                <thead>
                    <th>NOMBRE</th>
                    <th colspan="2"></th>
                    <th>CANTIDAD</th>
                </thead>
                <tbody>
                    <g:each in="${items}" var="${item}">
                        <tr>
                            <td colspan="4"><strong>${item.labName}</strong></td>
                        </tr>
                        <g:each in="${item.labProducts}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td colspan="2"></td>
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

        <g:if test="${medicines}">
            <table class="table table-hover">
                <caption>Medicinas</caption>
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>
                <thead>
                    <th>NOMBRE</th>
                    <th>PRESENTACION</th>
                    <th>MEDIDA</th>
                    <th>CANTIDAD</th>
                </thead>
                <tbody>
                    <g:each in="${medicines}" var="${medicine}">
                        <tr>
                            <td colspan="4">${medicine.labName}</td>
                        </tr>
                        <g:each in="${medicine.labProducts}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td>${product.presentation}</td>
                                <td>${product.measure}</td>
                                <td>${product.quantity}</td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin medicinas que mostrar</p>
        </g:else>

        <g:if test="${brandProducts}">
            <table class="table table-hover">
                <caption>Productos</caption>
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>
                <thead>
                    <th>NOMBRE</th>
                    <th>MARCA</th>
                    <th>DETALLE</th>
                    <th>CANTIDAD</th>
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
            <p>Sin productos de marca que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <g:link action="print" class="btn btn-default btn-block">
            Imprimir <span class="glyphicon glyphicon-print"></span>
        </g:link>

        <g:link action="send" class="btn btn-default btn-block">
            Enviar email <span class="glyphicon glyphicon-send"></span>
        </g:link>
    </content>
</g:applyLayout>
