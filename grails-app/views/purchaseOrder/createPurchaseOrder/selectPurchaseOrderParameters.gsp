<g:applyLayout name="twoColumns">
    <head>
        <title>Orden de compra</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
            <div class="col-md-5">
                <p>Parametros de orden de compra</p>
                <g:form autocomplete="off">
                    <g:render template="createPurchaseOrder/form"/>
                
                    <g:submitButton name="confirm" value="Continuar" class="btn btn-primary"/>
                    <g:submitButton name="cancel" value="Cancelar" class="btn btn-default"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
