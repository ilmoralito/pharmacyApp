<g:applyLayout name="twoColumns">
    <head>
        <title>Clientes</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, addTelefone"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-4">
                <g:form action="update" autocomplete="off">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="confirm" value="Actualizar" class="btn btn-primary"/>

                    <table class="table table-hover">
                        <caption>Telefonos</caption>
                        <tbody>
                            <g:each in="${client.telephones}" var="telephone">
                                <tr>
                                    <td>${telephone.telephoneNumber}</td>
                                    <td width="1">
                                        <g:link action="removeTelephone" params="[clientId: client.id, id: telephone.id]">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </g:link>
                                    </td>
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
