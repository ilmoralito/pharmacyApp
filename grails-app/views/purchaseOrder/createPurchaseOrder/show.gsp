<g:applyLayout name="twoColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, purchaseOrder"/>
    </head>

    <content tag="main">
            <div class="col-md-5">
                <p>Editar</p>
                <g:form autocomplete="off">
                    <g:render template="createPurchaseOrder/form"/>
                
                    <g:submitButton name="confirm" value="Confirmar" class="btn btn-primary"/>
                    <g:submitButton name="goBack" value="Regresar" class="btn btn-default"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
