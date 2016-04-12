<g:applyLayout name="threeColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        
    </content>

    <content tag="col1">
        <g:form action="update">
            <g:hiddenField name="id" value="${purchaseOrder.id}"/>

            <div class="form-group">
                <label for="invoiceNumber">Factura</label>
                <g:textField
                    name="invoiceNumber"
                    value="${purchaseOrder?.invoiceNumber}"
                    class="form-control"/>
            </div>

            <pharmacyApp:paymentTypeBox type="radio" paymentType="${purchaseOrder.paymentType}"/>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
