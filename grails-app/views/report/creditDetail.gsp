<g:applyLayout name="threeColumns">
    <head>
        <title>Detalle de credito</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${data}">
            <table class="table table-bordered table-hover">
                <caption>${data.size()} resultados para ${company}</caption>
                <colgroup>
                    <col span="1" style="width: 40%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 5%;">
                </colgroup>
                <thead>
                    <th>Empleado</th>
                    <th>Factura</th>
                    <th>Monto</th>
                    <th>Monto a la fecha</th>
                    <th>Pagado</th>
                </thead>
                <tbody>
                    <g:each in="${data}" var="d">
                        <tr>
                            <td rowspan="${d.credits.size() + 1}">${d.employee}</td>
                        </tr>
                        <g:each in="${d.credits}" var="c">
                            <tr>
                                <td>
                                    <g:link controller="payment" params="[creditSaleId: c.id]">
                                        ${c.invoiceNumber}
                                    </g:link>
                                </td>
                                <td>${c.balance}</td>
                                <td>${c.balanceToDate}</td>
                                <td>${c.paidOut ? "Si" : "No"}</td>
                            </tr>
                        </g:each>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
    <content tag="col1">
        <label>Monto de ${company}</label>
        <p>${companyBalance}</p>

        <g:form name="filter" action="creditDetail" autocomplete="off">
            <g:set var="paidOut" value="${params.list('paidOut')*.toBoolean()}"/>
            <g:hiddenField name="id" value="${id}"/>

            <label>Estado del credito</label>
            <div class="checkbox">
                <label>
                    <g:checkBox name="paidOut" value="false" checked="${false in paidOut || !paidOut}"/>
                    Pendiente
                </label>
            </div>

            <div class="checkbox">
                <label>
                    <g:checkBox name="paidOut" value="true" checked="${true in paidOut || !paidOut}"/>
                    Pagado
                </label>
            </div>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
