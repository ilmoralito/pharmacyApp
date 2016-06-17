<g:applyLayout name="twoColumns">
    <head>
        <title>Filtro</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form name="filterCreditSalesForm" action="filterCreditSales" autocomplete="off">
            <div class="form-group">
                <label for="q">Filtrar</label>
                <g:textField
                    name="q"
                    value="${params?.q}"
                    class="form-control"
                    autofocus="true"
                    placeholder="Buscar por numero de factura, nombre, empresa, inss o cedula del empleado"/>
            </div>

            <g:submitButton name="send" value="Buscar" class="btn btn-primary"/>
        </g:form>

        <g:if test="${creditSales}">
            <table class="table table-hover" style="margin-top: 5px;">
                <caption>${creditSales.size()} ${creditSales.size() > 1 ? 'resultados' : 'resultado'} para criterio ${params.q}</caption>
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 70%;">
                </colgroup>
                <thead>
                    <th>Factura</th>
                    <th>Trabajador</th>
                    <th>Empresa</th>
                </thead>
                <tbody>
                    <g:each in="${creditSales}" var="s">
                        <tr>
                            <td>
                                <g:link controller="payment" params="[creditSaleId: s.id]">
                                    <g:fieldValue bean="${s}" field="invoiceNumber"/>
                                </g:link>
                            </td>
                            <td><g:fieldValue bean="${s}" field="employee.fullName"/></td>
                            <td><g:fieldValue bean="${s}" field="employee.company.name"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p><br>Sin resultados que mostrar</p>
        </g:else>
    </content>
</g:applyLayout>
