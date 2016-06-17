<g:applyLayout name="twoColumns">
    <head>
        <title>Presentacion</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filter"/>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <div class="row">
                <div class="col-md-6">
                    <p>Presentacion</p>
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>
                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </div>

                <div class="col-md-6">
                    <p>Medidas</p>
                    <label>Filtrar medidas</label>
                    <pharmacyApp:measures presentation="${presentation}"/>
                </div>
            </div>
        </g:form>
    </content>
</g:applyLayout>
