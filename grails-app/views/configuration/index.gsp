<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="layout" content="distraction-free-layout"/>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
        <title>Configuracion</title>
    </head>
    <body>
        <p>Configuracion</p>
        <p>
            Definir valor minimos a tomar en cuenta al realizar
            notificaciones. Todos los valores son interpretados
            por dias.
        </p>

        <g:form name="form" action="index">
            <div class="form-group">
                <label for="minLowStocks">
                    Minima cantidad en productos en inventario
                </label>
                <input
                    type="number"
                    id="minLowStocks"
                    name="minLowStocks"
                    value="${configuration?.minLowStocks}"
                    class="form-control">
            </div>

            <div class="form-group">
                <label for="minMedicineOrdersAboutToExpire">
                    Minima de dias de medicinas por vencerse
                </label>
                <input
                    type="number"
                    id="minMedicineOrdersAboutToExpire"
                    name="minMedicineOrdersAboutToExpire"
                    value="${configuration?.minMedicineOrdersAboutToExpire}"
                    class="form-control">
            </div>

            <div class="form-group">
                <label for="minPurchaseOrdersPaymentDateClose">
                    Minima de dias de pedidos por pagar
                </label>
                <input
                    type="number"
                    id="minPurchaseOrdersPaymentDateClose"
                    name="minPurchaseOrdersPaymentDateClose"
                    value="${configuration?.minPurchaseOrdersPaymentDateClose}"
                    class="form-control">
            </div>

            <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
            <g:link controller="sale" class="btn btn-default pull-right">Salir y continuar</g:link>
        </g:form>
    </body>
</html>