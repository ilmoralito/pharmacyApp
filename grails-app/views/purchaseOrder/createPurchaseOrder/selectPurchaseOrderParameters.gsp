<g:applyLayout name="threeColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, bootstrap-tab, app"/>
    </head>

    <content tag="main">
        <p>Add items to purchase order</p>
    </content>

    <content tag="col1">

        <g:form>
            <g:render template="createPurchaseOrder/form"/>

            <g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
            <g:submitButton name="cancel" value="Cancelar" class="btn btn-default pull-right"/>
        </g:form>
    </content>
</g:applyLayout>
