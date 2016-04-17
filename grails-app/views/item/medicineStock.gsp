<g:applyLayout name="threeColumns">
    <head>
        <title>Inventario de medicinas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
    <g:render template="nav"/>

        <g:if test="${medicines}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 25%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Presentacion</th>
                    <th>Medida</th>
                    <th>Cantidad</th>
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
            <p>Sin artuclos que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <g:link action="print" class="btn btn-default btn-block">Imprimir</g:link>
    </content>
</g:applyLayout>
