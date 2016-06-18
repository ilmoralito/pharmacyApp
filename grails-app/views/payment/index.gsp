<g:applyLayout name="threeColumns">
    <head>
        <title>Abonos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${payments}">
            <table class="table table-hover">
                <caption>${creditSaleDescription}</caption>
                <colgroup>
                    <col span="1" style="width: 5%;">
                    <col span="1" style="width: 10%;">
                    <col span="1" style="width: 15%;">
                    <col span="1" style="width: 70%;">
                </colgroup>
                <thead>
                    <th class="text-center">#</th>
                    <th>Recibo</th>
                    <th>Fecha de abono</th>
                    <th>Cantidad abonada</th>
                </thead>
                <tbody>
                    <g:each in="${payments}" var="p" status="index">
                        <tr>
                            <td class="text-center">${index + 1}</td>
                            <td>
                                <g:link action="show" id="${p.id}">
                                    <g:fieldValue bean="${p}" field="receiptNumber"/>
                                </g:link>
                            </td>
                            <td><g:formatDate format="yyyy-MM-dd" date="${p.dateCreated}"/></td>
                            <td><g:fieldValue bean="${p}" field="amount"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Sin abonos que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="${!params.tab || params.tab != 'detail' ? 'active' : ''}">
                <g:link action="index" params="[creditSaleId: params.creditSaleId]">
                    Abono
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'detail' ? 'active' : '' }">
                <g:link action="index" params="[creditSaleId: params.creditSaleId, tab: 'detail']">
                    Detalle
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'detail'}">
            <g:form name="addPaymentForm" action="create" autocomplete="off">
                <g:hiddenField name="creditSaleId" value="${params.creditSaleId}"/>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="receiptNumber"># Recibo</label>
                            <g:textField name="receiptNumber" value="${params?.receiptNumber}" class="form-control"/>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="amount">Abono</label>
                            <g:textField name="amount" value="${params?.amount}" class="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="reference">Referencia</label>
                    <g:textField name="reference" value="${params?.reference}" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="madeBy">Abono realizado por</label>
                    <g:textField
                        name="madeBy"
                        value="${params?.madeBy}"
                        class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="madeByIdentityCard">Cedula de quien realizo el abono</label>
                    <g:textField name="madeByIdentityCard" value="${params?.madeByIdentityCard}" class="form-control"/>
                </div>

                <g:submitButton name="sendCreatePaymentForm" value="Confirmar" class="btn btn-primary btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <pharmacyApp:creditSaleDetail
                invoiceNumber="${creditSaleDetail.invoiceNumber}"
                balance="${creditSaleDetail.balance}"
                balanceToDate="${creditSaleDetail.balanceToDate}"
                paidOut="${creditSaleDetail.paidOut}"/>
        </g:else>
    </content>
</g:applyLayout>
