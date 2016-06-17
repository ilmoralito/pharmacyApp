<g:applyLayout name="twoColumns">
    <head>
        <title>Inventario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${result}">
            <g:if test="${!params?.flag}">
                <table class="table table-hover">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 60%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </thead>
                    <tbody>
                        <g:each in="${result}" var="${item}">
                            <tr>
                                <td colspan="2">${item.labName}</td>
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

            <g:if test="${params?.flag == 'medicines'}">
                <table class="table table-hover">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 60%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </thead>
                    <tbody>
                        <g:each in="${result}" var="medicine">
                            <tr>
                                <td colspan="4">${medicine.labName}</td>
                            </tr>
                            <g:each in="${medicine.labProducts}" var="product">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>${product.quantity}</td>
                                </tr>
                            </g:each>
                        </g:each>
                    </tbody>
                </table>
            </g:if>

            <g:if test="${params?.flag == 'brandProducts'}">
                <table class="table table-hover">
                    <colgroup>
                        <col span="1" style="width: 40%;">
                        <col span="1" style="width: 60%;">
                    </colgroup>
                    <thead>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </thead>
                    <tbody>
                        <g:each in="${result}" var="brandProduct">
                            <tr>
                                <td colspan="4">${brandProduct.labName}</td>
                            </tr>
                            <g:each in="${brandProduct.labProducts}" var="product">
                                <tr>
                                    <td>${product.name}</td>
                                    <td>${product.quantity}</td>
                                </tr>
                            </g:each>
                        </g:each>
                    </tbody>
                </table>
            </g:if>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
