<g:applyLayout name="threeColumnsDetail">
    <head>
        <title>Distribuidor</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <g:hiddenField name="id" value="${params?.id}"/>
            <g:render template="form"/>

            <g:submitButton name="update" value="Actualizar" class="btn btn-primary"/>
        </g:form>
    </content>
    <content tag="col1">
        <g:if test="${providers}">
            <p>Laboratorios</p>
            <table class="table table-hover">
                <tbody>
                    <g:each in="${providers}" var="provider">
                        <tr>
                            <td width="1">
                                <g:checkBox
                                    name="providers"
                                    form="form"
                                    value="${provider.id}"
                                    checked="${provider in dealer?.providers ?: false}"
                                    class="providers"/>
                            </td>
                            <td>${provider}</td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <g:form action="addRemoveProvider" name="form">
                <g:hiddenField name="id" value="${dealer.id}"/>

                <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
            </g:form>
        </g:if>
        <g:else>
            <p>
                Nada que mostrar
                <br>
                <small>Solo se mostraran laboratorios habilitados y que posean productos</small>
            </p>
        </g:else>
    </content>
</g:applyLayout>
