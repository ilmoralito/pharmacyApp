<g:applyLayout name="threeColumns">
    <head>
        <title>Presentaciones</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filter"/>
    </head>

    <content tag="main">
        <g:if test="${presentations}">
            <table class="table table-hover table-striped">
                <thead>
                    <th width="1" class="text-center">#</th>
                    <th>Nombre de presentacion</th>
                </thead>
                <tbody>
                    <g:each in="${presentations}" var="presentation" status="idx">
                        <tr>
                            <td class="text-center">${idx + 1}</td>
                            <td>
                                <g:link action="show" id="${presentation.id}">
                                    ${presentation.name}
                                </g:link>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:else>
            <p><br>Nada que mostrar</p>
        </g:else>
    </content>

    <content tag="col1">
        <g:form action="${actionName}" autocomplete="off">
            <g:render template="form"/>

            <p>Medidas</p>
            <pharmacyApp:measures/>

            <g:submitButton name="send" value="Agregar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
