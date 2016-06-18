<g:applyLayout name="threeColumns">
    <head>
        <title>Detalle de gasto diario</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${result}">
            <table class="table table-hover table-bordered">
                <caption>Gastio diario ${month} ${year}</caption>
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 50%;">
                    <col span="1" style="width: 20%;">
                </colgroup>
                <thead>
                    <th class="text-center">Dia</th>
                    <th>Realizado por</th>
                    <th>Descripcion</th>
                    <th>Cantidad</th>
                </thead>
                <tbody>
                    <g:each in="${result}" var="r">
                        <tr>
                            <td rowspan="${r.expenses.size() + 1 ?: 1}" class="text-center">${r.day}</td>
                            <g:if test="${!r.expenses}">
                                <td></td>
                                <td></td>
                                <td></td>
                            </g:if>
                        </tr>
                        <g:if test="${r.expenses}">
                            <g:each in="${r.expenses}" var="e">
                                <tr>
                                    <td>${e.fullName}</td>
                                    <td>${e.description}</td>
                                    <td>${e.quantity}</td>
                                </tr>
                            </g:each>
                        </g:if>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <g:if test="${resume}">
            <table class="table table-hover table-condensed">
                <caption>Resumen</caption>
                <thead>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                </thead>
                <tbody>
                    <g:each in="${resume}" var="r">
                        <tr>
                            <td>${r.fullName}</td>
                            <td>${r.quantity}</td>
                        </tr>
                    </g:each>
                    <tr>
                        <td></td>
                        <td>${resume.quantity.sum()}</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
