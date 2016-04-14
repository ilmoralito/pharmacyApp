<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, sale"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:form>
            <div class="form-group">
                <g:textField
                    name="query"
                    class="form-control"
                    autofocus="true"
                    placeholder="Filtrar"/>
            </div>
        </g:form>

        <table class="table table-hover table-striped">
            <colgroup>
                <col span="1" style="width: 15%;">
                <col span="1" style="width: 15%;">
                <col span="1" style="width: 70%;">
            </colgroup>
            <thead>
                <th>Nombre</th>
                <th>Laboratorio</th>
                <th>Precio</th>
            </thead>
            <tbody id="items"></tbody>
        </table>

        <table class="table table-hover table-striped">
            <colgroup>
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 15%;">
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 10%;">
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 5%;">
            </colgroup>
            <thead>
                <th>Nombre</th>
                <th>Laboratorio</th>
                <th>Presentacion</th>
                <th>Medida</th>
                <th>Marca</th>
                <th>Detalle</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Total</th>
                <th></th>
            </thead>
            <tbody id="itemsToSale">
                <tr>
                    <td colspan="8"></td>
                    <td colspan="2" id="summary"></td>
                </tr>
            </tbody>
        </table>

        <g:javascript>
            window.queryPATH = "${createLink(controller: 'sale', action: 'search')}"
        </g:javascript>
    </content>
    <content tag="col1">
        <g:form>
            <div class="form-group">
                <g:textField name="balance" class="form-control input-lg" placeholder="Saldo"/>
            </div>

            <div class="form-group">
                <g:textField name="ba" class="form-control input-lg" placeholder="Dinero"/>
            </div>

            <div class="form-group">
                <g:textField name="change" class="form-control input-lg" placeholder="Vuelto"/>
            </div>

            <button class="btn btn-default btn-primary btn-lg btn-block">Confirmar</button>
        </g:form>
    </content>
</g:applyLayout>
