<g:applyLayout name="threeColumns">
    <head>
        <title>Inventario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${items}">

            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 30%;">
                    <col span="1" style="width: 11%;">
                    <col span="1" style="width: 59%;">
                </colgroup>
                <thead>
                    <th>NOMBRE DEL PRODUCTO</th>
                    <th>N PEDIDOS</th>
                    <th>CANTIDAD</th>
                </thead>
                <tbody>
                    <g:each in="${items}" var="item">
                        <tr>
                            <td>${item.product}</td>
                            <td>${item.count}</td>
                            <td>${item.quantity}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td colspan="2"><b>TOTAL</b></td>
                        <td>${items.quantity.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
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
