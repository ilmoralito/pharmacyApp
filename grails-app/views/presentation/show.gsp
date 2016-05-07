<g:applyLayout name="twoColumns">
    <head>
        <title>Presentacion</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filter"/>
    </head>

    <content tag="main">
        <g:form action="update" autocomplete="off">
            <p>Presentacion</p>

            <div class="row">
                <div class="col-md-5">
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <label>Filtrar medidas</label>
                    <pharmacyApp:measures presentation="${presentation}"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </div>
            </div>
        </g:form>
    </content>
</g:applyLayout>
