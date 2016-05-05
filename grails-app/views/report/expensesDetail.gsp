<g:applyLayout name="twoColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${result}">
            <p>Gastos diario de ${month} ${year}</p>

            ${result}

            <table class="table table-hover table-bordered">
                <caption>${result.expenses}</caption>
                <colgroup>
                    <col span="1" style="width: 1%;">
                    <col span="1" style="width: 20%;">
                    <col span="1" style="width: 74%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Dia</th>
                    <th>Usuario</th>
                    <th>Descripcion</th>
                    <th>${r.expenses.size()}</th>
                </thead>
                <tbody>
                    <g:each in="${result}" var="r">
                        <tr>
                            <td rowspan="${r.expenses?.size() + 1 ?: 1}">${r.day}</td>
                        </tr>
                        <g:each in="${r.expenses}" var="e">
                            <tr>
                                <td>${e.fullName}</td>
                                <td>${e.description} ${e}</td>
                                <td>${e.quantity}</td>
                            </tr>
                                
                        </g:each>
                        
                    </g:each>
                    <tr>
                        <td colspan="3">Total</td>
                        <td>10000</td>
                    </tr>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
