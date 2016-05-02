<g:applyLayout name="threeColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${clients}">
            <table class="table table-hover">
                <colgroup>
                    <col span="1" style="width: 25%;">
                    <col span="1" style="width: 75%;">
                </colgroup>
                <thead>
                    <th>Cliente</th>
                    <th>Numero de compras</th>
                </thead>
                <tbody>
                    <g:each in="${clients}" var="c">
                        <tr>
                            <td>
                                <g:link
                                    action="detail"
                                    params="[id:c.id, client: c.client, field: params?.field ?: 'month']">
                                    ${c.client}
                                </g:link>
                            </td>
                            <td>${c.quantity}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
    <content tag="col1">
        <p>Intervalos</p>
        <g:render template="intervals" model="[target: 'clients']"/>
        
        <p style="margin-top: 10px;">Personalizado</p>
        <g:form action="clients" autocomplete="off">
            <g:hiddenField name="field" value="custom"/>
            <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
