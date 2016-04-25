<g:applyLayout name="twoColumns">
    <head>
        <title>Medicinas por vencerse</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${medicineOrders}">
            <p>Medicinas por vencerse</p>

            <table class="table table-hover table-striped">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <th>Medicamento</th>
                    <th>Dias para vencerse</th>
                </thead>
                <tbody>
                    <g:each in="${medicineOrders}" var="medicineOrder">
                        <tr>
                            <td>${item.product.name} ${item.presentation} ${item.measure}</td>
                            <td>${item.dueDate - new Date()}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
