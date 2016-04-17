<g:applyLayout name="threeColumns">
    <head>
        <title>Inventario de articulos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
    <g:render template="nav"/>

        <g:if test="${items}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 30%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                </thead>
                <tbody>
                    <g:each in="${items}" var="${item}">
                        <tr>
                            <td>${item.labName}</td>
                        </tr>
                        <g:each in="${item.labProducts}" var="product">
                            <tr>
                                <td>${product.name}</td>
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
