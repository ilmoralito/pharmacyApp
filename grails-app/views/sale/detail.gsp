<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:render template="saleDetailsTable" model="[saleDetails: sale.saleDetails]"/>
    </content>
    <content tag="col1">
        <p>Datos de venta</p>

        <label>ID</label>
        <p><g:fieldValue bean="${sale}" field="id"/></p>

        <label>Hora de venta</label>
        <p><g:formatDate format="HH:mm" date="${sale.dateCreated}"/></p>

        <label>Monto de compra</label>
        <p><g:fieldValue bean="${sale}" field="balance"/></p>

        <g:if test="${sale instanceof ni.sb.CashSale}">
            <label>Cantidad recivida</label>
            <p><g:fieldValue bean="${sale}" field="moneyReceived"/></p>

            <label>Cambio</label>
            <p>${sale.moneyReceived - sale.balance}</p>
        </g:if>

        <label>Atendido por</label>
        <p><g:fieldValue bean="${sale}" field="user.fullName"/></p>

        <g:if test="${sale instanceof ni.sb.CashSale}">
            <label>A nombre de</label>
            <p><g:fieldValue bean="${sale}" field="client.fullName"/></p>
        </g:if>

        <g:if test="${sale instanceof ni.sb.CreditSale}">
            <label>Numero de factura</label>
            <p><g:fieldValue bean="${sale}" field="invoiceNumber"/></p>

            <label>A nombre de</label>
            <p><g:fieldValue bean="${sale}" field="employee.fullName"/></p>

            <label>Empresa</label>
            <p><g:fieldValue bean="${sale}" field="employee.company.name"/></p>
        </g:if>

        <g:if test="${sale.annotation}">
            <label>Observacion</label>
            <p><g:fieldValue bean="${sale}" field="annotation"/></p>
        </g:if>

        <g:if test="${sale.canceled}">
            <label>Estado</label>
            <p>Anulada</p>

            <label>Fecha y hora de anulacion</label>
            <p><g:formatDate format="yyyy-MM-dd HH:mm" date="${sale.dateOfCancelation}"/></p>

            <label>Motivo de la anulacion</label>
            <p><g:fieldValue bean="${sale}" field="reazonOfCanelation"/></p>
        </g:if>

        <g:if test="${!sale.canceled}">
            <p>Tarea</p>
            <g:form name="form" action="cancelSale">
                <g:hiddenField name="id" value="${sale.id}"/>

                <div class="form-group">
                    <g:textArea
                        name="reazonOfCanelation"
                        class="form-control"
                        maxLength="255"
                        placeholder="Motivo de anulacion"/>
                </div>

                <g:submitButton name="send" value="Confirmar anulacion" class="btn btn-danger btn-block"/>
            </g:form>
        </g:if>
        <g:else>
            <div class="alert alert-info">
                Venta anulada
            </div>
        </g:else>
    </content>
</g:applyLayout>
