<g:applyLayout name="twoColumns">
    <head>
        <title>Presentacion</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, filter"/>
    </head>

    <content tag="main">
        <div class="row">
            <div class="col-md-6">
                <g:form action="update" autocomplete="off">
                    <p>Presentacion</p>
                    <g:hiddenField name="id" value="${params?.id}"/>
                    <g:render template="form"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
            <div class="col-md-6">
                <g:form action="addRemoveMeasure" autocomplete="off">
                    <p>Medidas</p>
                    <g:hiddenField name="id" value="${presentation.id}"/>
                    <pharmacyApp:measures presentation="${presentation}"/>

                    <g:submitButton name="send" value="Confirmar" class="btn btn-primary"/>
                </g:form>
            </div>
        </div>
    </content>
</g:applyLayout>
