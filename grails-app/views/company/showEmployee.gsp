<g:applyLayout name="twoColumns">
    <head>
        <title>Empleado</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <p>
                    <g:link action="employees" id="${employee.company.id}">
                        Regresar
                    </g:link>
                </p>

                <g:form action="updateEmployee" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="formEmployee"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
