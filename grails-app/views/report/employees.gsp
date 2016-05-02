<g:applyLayout name="threeColumns">
    <head>
        <title>A empleados</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app, bootstrap3datepicker, fromToDatepicker"/>
    </head>

    <content tag="main">
        <g:render template="navbar"/>

        <g:if test="${sales}">
            
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
    <content tag="col1">
        <p style="margin-top: 10px;">Personalizado</p>
        <g:form>
            <g:hiddenField name="field" value="custom"/>
            <pharmacyApp:fromTo from="${params?.from}" to="${params?.to}"/>

            <g:submitButton name="send" value="Filtrar" class="btn btn-primary btn-block"/>
        </g:form>
    </content>
</g:applyLayout>
