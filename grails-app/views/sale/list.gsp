<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, app"/>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:if test="${sales}">
            <!--if logic-->
        </g:if>
        <g:else>
            <p>Nada que mostrar</p>
        </g:else>
    </content>
    <content tag="col1">
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa consequuntur quidem saepe cumque, delectus magni nesciunt labore eveniet voluptas dolorum consequatur aliquid sed dolore obcaecati sint neque nobis architecto in.
    </content>
</g:applyLayout>
