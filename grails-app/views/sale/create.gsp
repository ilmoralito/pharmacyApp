<g:applyLayout name="threeColumns">
    <head>
        <title>Ventas</title>
        <r:require modules="bootstrap-css, bootstrap-collapse, sale"/>
        <g:javascript>
            window.queryPATH = "${createLink(controller: 'sale', action: 'search')}"
        </g:javascript>
    </head>

    <content tag="main">
        <g:render template="nav"/>

        <g:form>
            <div class="form-group">
                <g:textField
                    name="query"
                    class="form-control"
                    autofocus="true"
                    placeholder="Filtrar"/>
            </div>
        </g:form>
    </content>
    <content tag="col1">
        Lorem ipsum dolor sit amet
        consectetur adipisicing elit. Culpa consequuntur quidem saepe cumque, delectu
        magni nesciunt labore eveniet voluptas dolorum consequatur aliquid sed
        dolore obcaecati sint neque nobis architecto in.
    </content>
</g:applyLayout>
