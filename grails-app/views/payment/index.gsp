<g:applyLayout name="threeColumns">
    <head>
        <title>Abonos</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:if test="${creditSale.payments}">
            <table class="table table-hover">
                <thead>
                    <th>Numero de recibo</th>
                </thead>
                <tbody>
                    <g:each in="${creditSale.payments}" var="payment">
                        <tr>
                            <td>
                                <g:link action="show" id="${payment.id}">
                                    <g:fieldValue bean="${payment}" field="receiptNumber"/>
                                </g:link>
                            </td>
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
            <li role="presentation" class="${!params.tab || params.tab != 'payment' ? 'active' : ''}">
                <g:link action="index" params="[creditSaleId: params.creditSaleId]">
                    Detalle
                </g:link>
            </li>
            <li role="presentation" class="${params.tab == 'payment' ? 'active' : '' }">
                <g:link action="index" params="[creditSaleId: params.creditSaleId, tab: 'payment']">
                    Abono
                </g:link>
            </li>
        </ul>

        <g:if test="${!params.tab || params.tab != 'payment'}">
            <pharmacyApp:creditSaleDetail
                invoiceNumber="${creditSaleDetail.invoiceNumber}"
                balanceToDate="${creditSaleDetail.balanceToDate}"/>
        </g:if>
        <g:else>
            <g:form name="createPaymentForm" action="index" params="[creditSaleId: params.creditSaleId, tab: 'payment']" autocomplete="off">
                <g:hiddenField name="creditSaleId" value="${creditSale.id}"/>

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
        </g:else>
    </content>
</g:applyLayout>
